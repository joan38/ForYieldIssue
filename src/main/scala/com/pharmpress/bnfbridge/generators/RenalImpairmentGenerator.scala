package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.RenalImpairment


object RenalImpairmentGenerator {

  def apply(renalImpairment: RenalImpairment) =
    <renalImpairment id={renalImpairment.id}
                     drugId={renalImpairment.parentId}
                     publication="BNF"
                     type="POT"
                     xsi:noNamespaceSchemaLocation="renalImpairment.xsd"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <doseAdjustments>{renalImpairment.doseAdjustments}</doseAdjustments>
      <generalInformation>{renalImpairment.generalInformation}</generalInformation>
    </renalImpairment>
}
