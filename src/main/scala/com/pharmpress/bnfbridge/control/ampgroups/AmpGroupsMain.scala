package com.pharmpress.bnfbridge.control.ampgroups

import com.pharmpress.bnfbridge.control.AutoIncIdGenerator
import com.pharmpress.bnfbridge.control.CsvUtils._
import com.tinkerpop.blueprints.impls.neo4j2.Neo4j2Graph


object AmpGroupsMain extends App {
  private val AmpGroupsOutputPath = "target/output/ampGroups"
  private val FormsOutputPath = "target/output/forms"
  private val AmpGroupStartId = 0
  private val FormsStartId = 3441
  private val SheetToDo = Seq(1)
  private val DataBasePath = "target/database"
  private val FormIdsProductionExportPath = "resources/formIdsProductionExport.csv"
  private val AmpSpreadsheetPath = "resources/ampSpreadsheet.xlsx"
  private val Preview = false

  val graph = new Neo4j2Graph(DataBasePath)
  try {
    AmpGroupsBridger(
      graph,
      AmpSpreadsheetPath,
      importFormsFromProduction(FormIdsProductionExportPath),
      AmpGroupsOutputPath,
      FormsOutputPath,
      new AutoIncIdGenerator("GROUP", AmpGroupStartId),
      new AutoIncIdGenerator("FORM", FormsStartId),
      Preview,
      SheetToDo
    )
  } finally graph.shutdown()
}
