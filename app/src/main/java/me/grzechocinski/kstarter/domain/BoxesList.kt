package me.grzechocinski.kstarter.domain

class BoxesList(boxes: List<Box>){

  private val value = convert(boxes)

  val size get() = value.size

  private fun convert(boxes: List<Box>): List<Box> {
    return (boxes + boxes).mapIndexed { index, box -> box.copy(header = box.header + " #$index") }
  }

  operator fun get(i: Int): Box = value[i]
}
