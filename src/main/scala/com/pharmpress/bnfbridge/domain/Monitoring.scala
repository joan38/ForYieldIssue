package com.pharmpress.bnfbridge.domain

case class Monitoring(id: String,
                      parentId: String,
                      therapeuticDrugMonitoring: String = "",
                      monitoringOfPatientParameters: String = "",
                      patientMonitoringProgrammes: String = "") extends Pot