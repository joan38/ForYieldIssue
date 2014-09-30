package com.pharmpress.bnfbridge.domain

case class AmpGroup(id: String,
                    title: String,
                    formIds: Seq[String] = Seq.empty,
                    amps: Seq[Amp] = Seq.empty)

case class Amp(id: String,
               title: String)