package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.SideEffects


object SideEffectsGenerator {

  def apply(sideEffects: SideEffects) =
    <sideEffects id={sideEffects.id}
                 drugId={sideEffects.parentId}
                 publication="BNF"
                 type="POT"
                 xsi:noNamespaceSchemaLocation="sideEffects.xsd"
                 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <advice>{sideEffects.advice}</advice>
      <overdosageInformation></overdosageInformation>
    </sideEffects>
}
