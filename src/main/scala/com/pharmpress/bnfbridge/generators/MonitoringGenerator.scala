package com.pharmpress.bnfbridge.generators

import com.pharmpress.bnfbridge.domain.Monitoring


object MonitoringGenerator {

  def apply(monitoring: Monitoring) =
    <monitoring id={monitoring.id}
                drugId={monitoring.parentId}
                publication="BNF"
                xsi:noNamespaceSchemaLocation="monitoring.xsd"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
      <therapeuticDrugMonitoring>{monitoring.therapeuticDrugMonitoring}</therapeuticDrugMonitoring>
      <monitoringOfPatientParameters>{monitoring.monitoringOfPatientParameters}</monitoringOfPatientParameters>
      <patientMonitoringProgrammes>{monitoring.patientMonitoringProgrammes}</patientMonitoringProgrammes>
    </monitoring>
}
