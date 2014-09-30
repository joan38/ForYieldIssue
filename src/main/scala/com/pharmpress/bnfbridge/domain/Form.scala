package com.pharmpress.bnfbridge.domain

case class Form(id: String,
                drugId: String,
                title: String,
                labels: List[String] = List.empty,
                electrolytes: List[String] = List.empty,
                excipients: List[String] = List.empty)