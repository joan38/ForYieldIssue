package com.pharmpress.bnfbridge.domain

case class ConceptionAndContraception(id: String,
                                      parentId: String,
                                      doseAdjustments: String = "",
                                      generalInformation: String = "") extends Pot