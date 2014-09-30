package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.Administration


object AdministrationGenerator {

  def apply(administration: Administration) =
    <administration id={administration.id}
                    drugId={administration.parentId}
                    publication="BNF"
                    type="POT"
                    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xsi:noNamespaceSchemaLocation="administration.xsd"></administration>
}
