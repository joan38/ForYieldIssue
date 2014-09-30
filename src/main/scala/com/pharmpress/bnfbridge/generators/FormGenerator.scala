package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.Form


object FormGenerator {

  def apply(form: Form) =
    <form id={form.id}
          drugId={form.drugId}
          publication="BNF"
          type="FORM"
          xsi:noNamespaceSchemaLocation="form.xsd"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <title>{form.title}</title>
    </form>
}
