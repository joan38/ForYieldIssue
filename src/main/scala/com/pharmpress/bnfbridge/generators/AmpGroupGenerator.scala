package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.AmpGroup


object AmpGroupGenerator {

  def apply(ampGroup: AmpGroup) =
    <ampGroup id={ampGroup.id}
              publication="BNF"
              type="GROUP"
              xsi:noNamespaceSchemaLocation="ampGroup.xsd"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <title>{ampGroup.title}</title>
      <formIds>
        {ampGroup.formIds map (formId => <formId>{formId}</formId>)}
      </formIds>
      <amps>
        {ampGroup.amps map (amp =>
        <amp>
          <name>{amp.title}</name>
          <dmdId>{amp.id}</dmdId>
        </amp>)}
      </amps>
    </ampGroup>
}
