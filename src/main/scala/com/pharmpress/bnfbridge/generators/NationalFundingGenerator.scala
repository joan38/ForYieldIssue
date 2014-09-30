package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.NationalFunding


object NationalFundingGenerator {

  def apply(nationalFunding: NationalFunding) =
    <nationalFunding id={nationalFunding.id}
                     drugId={nationalFunding.parentId}
                     publication="BNF"
                     xsi:noNamespaceSchemaLocation="nationalFunding.xsd"
                     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    </nationalFunding>

}
