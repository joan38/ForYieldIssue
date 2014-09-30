package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.HepaticImpairment


object HepaticImpairmentGenerator {

  def apply(hepaticImpairment: HepaticImpairment) =
    <hepaticImpairment id={hepaticImpairment.id}
                       drugId={hepaticImpairment.parentId}
                       publication="BNF"
                       type="POT"
                       xsi:noNamespaceSchemaLocation="hepaticImpairment.xsd"
                       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <doseAdjustments>{hepaticImpairment.doseAdjustments}</doseAdjustments>
      <generalInformation>{hepaticImpairment.generalInformation}</generalInformation>
    </hepaticImpairment>

}
