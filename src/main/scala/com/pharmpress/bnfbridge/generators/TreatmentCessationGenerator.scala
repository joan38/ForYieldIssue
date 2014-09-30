package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.TreatmentCessation


object TreatmentCessationGenerator {

  def apply(treatmentCessation: TreatmentCessation) =
      <treatmentCessation id={treatmentCessation.id}
                          drugId={treatmentCessation.parentId}
                          publication="BNF"
                          type="POT"
                          xsi:noNamespaceSchemaLocation="treatmentCessation.xsd"
                          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"></treatmentCessation>
}
