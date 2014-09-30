package com.pharmpress.bnfbridge.domain

case class RenalImpairment(id: String,
                           parentId: String,
                           doseAdjustments: String = "",
                           generalInformation: String = "") extends Pot