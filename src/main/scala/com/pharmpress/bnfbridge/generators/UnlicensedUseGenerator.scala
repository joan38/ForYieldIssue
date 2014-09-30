package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.UnlicensedUse


object UnlicensedUseGenerator {

  def apply(unlicensedUse: UnlicensedUse) =
    <unlicensedUse id={unlicensedUse.id}
                   drugId={unlicensedUse.parentId}
                   publication="BNF"
                   type="POT"
                   xsi:noNamespaceSchemaLocation="unlicensedUse.xsd"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    </unlicensedUse>
}
