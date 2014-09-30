package com.pharmpress.bnfbridge.control.ampgroups

import java.io.{File, FileInputStream}
import java.nio.file.{Files, Paths}
import java.io.FileOutputStream

import com.pharmpress.bnfbridge.control.{XmlSaver, AutoIncIdGenerator}
import com.pharmpress.bnfbridge.domain.{Amp, AmpGroup, Form}
import com.pharmpress.bnfbridge.generators.{FormGenerator, AmpGroupGenerator}
import com.tinkerpop.blueprints.Graph
import com.tinkerpop.gremlin.scala._
import com.tinkerpop.gremlin.java.GremlinPipeline
import org.apache.poi.ss.usermodel.{Cell, WorkbookFactory}
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._
import scala.util.Try


object AmpGroupsBridger {
  private val Log = LoggerFactory.getLogger(getClass)
  private val Separator = """\s?\|\s?"""
  private val Imported = "Imported"

  def apply(graph: Graph,
            ampSpreadsheetPath: String,
            forms: Seq[Form],
            ampGroupsOutputPath: String,
            formsOutputPath: String,
            autoIncAmpGroupId: Iterator[String] = new AutoIncIdGenerator("GROUP"),
            autoIncFormId: Iterator[String] = new AutoIncIdGenerator("FORM"),
            preview: Boolean,
            sheetsToDo: Seq[Int]): Seq[AmpGroup] = {
    Log debug "Bridging AmpGroups"

    val success = if (preview) "Ok" else Imported

    Files.createDirectories(Paths.get(ampGroupsOutputPath))
    Files.createDirectories(Paths.get(formsOutputPath))

    val formsGroupedByPhpId = forms groupBy (_.drugId)
    val ampSpreadsheetFile = new File(ampSpreadsheetPath)
    val document = WorkbookFactory.create(ampSpreadsheetFile)

    def logEmptyCell(name: String, importedCell: Cell) = {
      val message = s"Empty $name"
      Log info message
      importedCell setCellValue message
      false
    }

    val ampGroup = for {
      sheetToDo <- sheetsToDo
      row <- document.getSheetAt(sheetToDo).toList
      if row.getRowNum != 0
      _ = Log trace s"Reading line ${row.getRowNum}"

      // Imported cell
      importedCell = Option(row.getCell(7)) getOrElse row.createCell(7)
      _ = importedCell setCellType Cell.CELL_TYPE_STRING
      if importedCell.getStringCellValue != Imported

      // PHP ID
      phpIdsCell = row.getCell(1)
      if phpIdsCell != null || logEmptyCell("PHP ID", importedCell)
      _ = phpIdsCell.setCellType(Cell.CELL_TYPE_STRING)
      phpIds = phpIdsCell.getStringCellValue split Separator map (_.trim) filter (_.nonEmpty)
      if phpIds.nonEmpty || logEmptyCell("PHP ID", importedCell)

      // Medicinal product name
      titleCell = row.getCell(4)
      if titleCell != null || logEmptyCell("medicinal product name", importedCell)
      _ = titleCell.setCellType(Cell.CELL_TYPE_STRING)
      title = titleCell.getStringCellValue.trim
      if title.nonEmpty || logEmptyCell("medicinal product name", importedCell)

      // DM+D
      dmdIdsCell = row.getCell(5)
      if dmdIdsCell != null || logEmptyCell("DM+D", importedCell)
      _ = dmdIdsCell.setCellType(Cell.CELL_TYPE_STRING)
      dmdIds = dmdIdsCell.getStringCellValue split Separator map (_.trim) filter (_.nonEmpty)
      if dmdIds.nonEmpty || logEmptyCell("AMP ID", importedCell)

      _ = Log trace s"Query Forms ${dmdIds.mkString(",")} in the Graph"
      forms = dmdIds flatMap { dmdId =>
	      new GremlinPipeline(graph).V().has("dmdid", dmdId).has("type", "AMP")
          .both("has").has("type", "VMP")
          .both("has").has("type", "DFORM")
          .both("has").has("type", "FORM")
          .property("desc").toList.headOption map
          ((_, dmdId)) orElse {
          val message = s"The AMP ID '$dmdId' doesn't exist"
          Log info message
          importedCell setCellValue message
          None
        }
	    }
	    if forms.size == dmdIds.size

	    dmdIdsGroupedByForms = forms groupBy (_._1) mapValues (_ map (_._2))
	    if dmdIdsGroupedByForms.size == 1 || {
        val message = "Multiple Forms on one row: " + (dmdIdsGroupedByForms map { case (form, dmdIds) =>
          s"$form (${dmdIds.mkString(", ")})"
        }).mkString(", ")
        Log info message
        importedCell setCellValue message
        false
	    }
      form = dmdIdsGroupedByForms.head._1

      _ = Log trace s"Looking for Form IDs for AmpGroup $title"
      formIds = phpIds flatMap { phpId =>
        formsGroupedByPhpId.getOrElse(phpId, Nil) filter (_.title == form) match {
          case Nil =>
            val message = s"The form '$form' can't be found for the PHP ID '$phpId'. Will try to create one with VMP"
            Log debug message
            importedCell setCellValue message

            val vmpForm = Try {
              val vmpIdsCell = row.getCell(3)
              vmpIdsCell.setCellType(Cell.CELL_TYPE_STRING)
              (vmpIdsCell.getStringCellValue split Separator map (_.trim) filter (_.nonEmpty)).head
            }.toOption orElse {
              val message = s"The form '$form' can't be found for the PHP ID '$phpId' and can't read the VMP ID cell"
              Log info message
              importedCell setCellValue message
              None
            } flatMap { vmpId =>
              new GremlinPipeline(graph).V().has("dmdid", vmpId).has("type", "VMP")
                .both("has").has("type", "DFORM")
                .both("has").has("type", "FORM").property("desc")
                .cast(classOf[String]).toList.headOption orElse {
                val message = s"Can't find any form for the VMP ID '$vmpId'"
                Log info message
                importedCell setCellValue message
                None
              }
            } map { formTitle: String =>
              Form(
                id = autoIncFormId.next(),
                drugId = phpId,
                title = formTitle
              )
            } map { form =>
              XmlSaver(FormGenerator(form), formsOutputPath)
              form
            }

            vmpForm.toSeq
          case forms => forms
        }
      } map (_.id)
      if formIds.size == phpIds.size

      _ = Log trace s"Querying AMPs for AmpGroup $title"
      amps = dmdIds flatMap { dmdId =>
        new GremlinPipeline(graph).V().has("dmdid", dmdId).has("type", "AMP").property("nm").cast(classOf[String]).toList.headOption map { title =>
          Seq(Amp(dmdId, title))
        } getOrElse {
          val message = s"The DMD ID '$dmdId' doesn't exist"
          Log info message
          importedCell setCellValue message
          Nil
        }
      }
      if amps.nonEmpty

      ampGroup = AmpGroup(
        autoIncAmpGroupId.next(),
        title,
        formIds,
        amps
      )

      _ = Log trace s"Saving AmpGroup $title"
      _ = XmlSaver(AmpGroupGenerator(ampGroup), ampGroupsOutputPath)

      _ = importedCell setCellValue success
    } yield ampGroup

    // Write a new Excel file with the result
    val excelOutputStream = new FileOutputStream(ampGroupsOutputPath + File.separator + ampSpreadsheetFile.getName)
    try {
      document.write(excelOutputStream)
    } finally excelOutputStream.close()

    ampGroup
  }
}
