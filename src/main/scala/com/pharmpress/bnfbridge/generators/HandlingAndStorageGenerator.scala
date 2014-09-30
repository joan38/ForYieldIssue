package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.HandlingAndStorage


object HandlingAndStorageGenerator {

  def apply(handlingAndStorage: HandlingAndStorage) =
    <handlingAndStorage id={handlingAndStorage.id}
                        drugId={handlingAndStorage.parentId}
                        publication="BNF"
                        type="POT"
                        xsi:noNamespaceSchemaLocation="handlingAndStorage.xsd"
                        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    </handlingAndStorage>

}
