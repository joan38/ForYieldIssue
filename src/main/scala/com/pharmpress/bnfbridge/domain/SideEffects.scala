package com.pharmpress.bnfbridge.domain

case class SideEffects(id: String,
                       parentId: String,
                       advice: String = "") extends Pot