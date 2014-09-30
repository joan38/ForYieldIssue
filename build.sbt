name := "BNF Bridge"

version := "2.0"

libraryDependencies ++= Seq(
  "com.github.scopt" %% "scopt" % "3.2.0",
  "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.2",
  "com.michaelpollmeier" %% "gremlin-scala" % "2.5.3",
  "com.tinkerpop.blueprints" % "blueprints-neo4j2-graph" % "2.5.0",
  "com.github.tototoshi" %% "scala-csv" % "1.0.0",
  "org.apache.poi" % "poi-ooxml" % "3.10-FINAL"
)
