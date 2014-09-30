package com.pharmpress.bnfbridge.domain

case class HepaticImpairment(id: String,
                             parentId: String,
                             doseAdjustments: String = "",
                             generalInformation: String = "") extends Pot