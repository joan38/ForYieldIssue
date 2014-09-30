package com.pharmpress.bnfbridge.control

import scala.xml._


object XmlSaver {
  private val XmlExtension = ".xml"
  private val Encoding = "utf-8"

  def apply(xml: Elem, path: String) = {
    val id = (xml \ "@id").text
    XML.save(s"$path/$id$XmlExtension", xml, Encoding, true)
  }
}
