package me.grzechocinski.kstarter.domain

class BoxesList(boxes: List<Box>) {

  private val value = convert(boxes)

  private fun convert(boxes: List<Box>): List<Box> {
    return boxes.mapIndexed { index, box -> box.copy(header = "${box.header} #$index") }
  }

  operator fun get(i: Int): Box = value[i]
}
