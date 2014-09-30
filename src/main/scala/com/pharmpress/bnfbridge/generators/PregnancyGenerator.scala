package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.Pregnancy


object PregnancyGenerator {

  def apply(pregnancy: Pregnancy) =
    <pregnancy id={pregnancy.id}
               drugId={pregnancy.parentId}
               publication="BNF"
               xsi:noNamespaceSchemaLocation="pregnancy.xsd"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <doseAdjustments>{pregnancy.doseAdjustments}</doseAdjustments>
      <generalInformation>{pregnancy.generalInformation}</generalInformation>
   </pregnancy>
}
