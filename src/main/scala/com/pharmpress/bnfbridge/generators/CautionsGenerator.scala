package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.Cautions


object CautionsGenerator {

  def apply(cautions: Cautions) =
    <cautions id={cautions.id}
              drugId={cautions.parentId}
              publication="BNF"
              type="POT"
              xsi:noNamespaceSchemaLocation="cautions.xsd"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    </cautions>

}
