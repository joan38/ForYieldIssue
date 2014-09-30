package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.PatientAdvice


object PatientAdviceGenerator {

  def apply(patientAdvice: PatientAdvice) =
    <patientAdvice id={patientAdvice.id}
                   drugId={patientAdvice.parentId}
                   publication="BNF"
                   xsi:noNamespaceSchemaLocation="patientAdvice.xsd"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <adviceAroundMissedDoses></adviceAroundMissedDoses>
      <adviceAroundDrivingAndOtherTasks></adviceAroundDrivingAndOtherTasks>
      <patientResources></patientResources>
      <generalAdvice></generalAdvice>
    </patientAdvice>
}
