package me.grzechocinski.kstarter.domain

class BoxesList(boxes: List<Box>) {

  private val value = convert(boxes)

  private fun convert(boxes: List<Box>): List<Box> {
    boxes.mapIndexed { index, box -> box.header = "${box.header} #$index" }
    return boxes
  }

  operator fun get(i: Int): Box = value[i]
}
