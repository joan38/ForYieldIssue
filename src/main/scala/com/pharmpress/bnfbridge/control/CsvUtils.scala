package com.pharmpress.bnfbridge.control

import com.github.tototoshi.csv.{CSVReader, CSVWriter}
import com.pharmpress.bnfbridge.domain.Form


object CsvUtils {

  def makeReport(path: String, titles: Iterable[String], lines: Iterable[Iterable[Any]]) {
    val csvWriter = CSVWriter.open(path)
    csvWriter.writeAll(titles.toSeq +: lines.toSeq map (_.toSeq))
    csvWriter.close()
  }

  // Fix to catch up from the already introduced Forms in production
  def importFormsFromProduction(path: String) =
    CSVReader.open(path).all map { line =>
      Form(line(1).trim, line(0).trim, line(2).trim)
    }
}
