package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.IndicationDose


object IndicationDoseGenerator {

  def apply(indicationAndDose: IndicationDose) =
    <indicationsDose id={indicationAndDose.id}
                     drugId={indicationAndDose.parentId}
                     publication="BNF"
                     type="POT"
                     xsi:noNamespaceSchemaLocation="../schema/indicationsDose.xsd"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    </indicationsDose>
}
