package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.ConceptionAndContraception


object ConceptionAndContraceptionGenerator {

  def apply(conceptionAndContraception: ConceptionAndContraception) =
    <conceptionAndContraception id={conceptionAndContraception.id}
                                drugId={conceptionAndContraception.parentId}
                                publication="BNF"
                                type="POT"
                                xsi:noNamespaceSchemaLocation="conceptionAndContraception.xsd"
                                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <doseAdjustments>{conceptionAndContraception.doseAdjustments}</doseAdjustments>
      <generalInformation>{conceptionAndContraception.generalInformation}</generalInformation>
    </conceptionAndContraception>
}
