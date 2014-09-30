package com.pharmpress.bnfbridge.control

import java.util.concurrent.atomic.AtomicLong

class AutoIncIdGenerator(prefix: String, index: Long = 0) extends Iterator[String] {

  private val atomicIndex = new AtomicLong(index)

  def currentIndex = atomicIndex.get()

  def next() = prefix + atomicIndex.incrementAndGet()

  def hasNext: Boolean = true
}