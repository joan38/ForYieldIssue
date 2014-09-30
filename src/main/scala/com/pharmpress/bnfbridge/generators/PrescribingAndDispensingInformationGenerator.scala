package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.PrescribingAndDispensingInformation


object PrescribingAndDispensingInformationGenerator {

  def apply(prescribingAndDispensingInformation: PrescribingAndDispensingInformation) =
    <prescribingAndDispensingInformation id={prescribingAndDispensingInformation.id}
              drugId={prescribingAndDispensingInformation.parentId}
              publication="BNF"
              type="POT"
              xsi:noNamespaceSchemaLocation="prescribingAndDispensingInformation.xsd"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    </prescribingAndDispensingInformation>

}
