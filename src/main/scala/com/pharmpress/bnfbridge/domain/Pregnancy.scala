package com.pharmpress.bnfbridge.domain

case class Pregnancy(id: String,
                     parentId: String,
                     doseAdjustments: String = "",
                     generalInformation: String = "") extends Pot