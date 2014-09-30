package com.pharmpress.bnfbridge.domain

case class PatientAdvice(id: String,
                         parentId: String,
                         adviceAroundMissedDoses: String = "",
                         adviceAroundDrivingAndOtherTasks: String = "",
                         patientResources: String = "",
                         generalAdvice: String = "") extends Pot