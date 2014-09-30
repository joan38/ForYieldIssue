package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.LessSuitable


object LessSuitableGenerator {

  def apply(lessSuitable: LessSuitable) =
    <lessSuitable id={lessSuitable.id}
                  drugId={lessSuitable.parentId}
                  publication="BNF"
                  type="POT"
                  xsi:noNamespaceSchemaLocation="lessSuitable.xsd"
                  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    </lessSuitable>
}
