package com.pharmpress.bnfbridge.domain

case class BreastFeeding(id: String,
                         parentId: String,
                         doseAdjustments: String = "",
                         generalInformation: String = "") extends Pot
