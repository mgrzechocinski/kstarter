package com.empik.empikapp.browser.domain.box

import com.memoizr.assertk.expect
import me.grzechocinski.kstarter.domain.Box
import me.grzechocinski.kstarter.domain.BoxesList
import org.junit.Test

internal class BoxesListTest {

  @Test
  fun `should add index suffix to every box header`() {
    //given
    val single1 = Box(header = "Ksiązka")
    val single2 = Box(header = "Film")
    val single3 = Box(header = "Muzyka")

    val boxes = listOf(single1, single2, single3)

    //when
    val boxesList = BoxesList(boxes + boxes)

    //then
    expect that boxesList[0].header isEqualTo "Ksiązka #0"
    expect that boxesList[1].header isEqualTo "Film #1"
    expect that boxesList[2].header isEqualTo "Muzyka #2"

    expect that boxesList[3].header isEqualTo "Ksiązka #3"
    expect that boxesList[4].header isEqualTo "Film #4"
    expect that boxesList[5].header isEqualTo "Muzyka #5"
  }
}