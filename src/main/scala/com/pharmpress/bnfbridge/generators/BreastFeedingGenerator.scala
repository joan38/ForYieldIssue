package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.BreastFeeding


object BreastFeedingGenerator {

  def apply(breastFeeding: BreastFeeding) =
    <breastFeeding id={breastFeeding.id}
                   drugId={breastFeeding.parentId}
                   publication="BNF"
                   type="POT"
                   xsi:noNamespaceSchemaLocation="breastFeeding.xsd"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <doseAdjustments>{breastFeeding.doseAdjustments}</doseAdjustments>
      <generalInformation>{breastFeeding.generalInformation}</generalInformation>
    </breastFeeding>
}
