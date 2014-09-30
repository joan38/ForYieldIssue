package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.Contraindications


object ContraindicationsGenerator {

  def apply(contraindications: Contraindications) =
    <contraindications id={contraindications.id}
                       drugId={contraindications.parentId}
                       publication="BNF"
                       type="POT"
                       xsi:noNamespaceSchemaLocation="contraindications.xsd"
                       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    </contraindications>
}
