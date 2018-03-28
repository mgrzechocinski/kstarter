package me.grzechocinski.kstarter

import com.memoizr.assertk.expect
import me.grzechocinski.kstarter.domain.Box
import me.grzechocinski.kstarter.domain.BoxesList
import org.junit.Test

class ImmutabilityTest {

  @Test
  fun `should treat class as immutable`() {
    //given
    val boxes = listOf(
      Box("Ksiązka"),
      Box("Film"),
      Box("Muzyka")
    )

    //when
    val boxesList = BoxesList(boxes)

    //then
    expect that boxesList[0].header isEqualTo "Ksiązka #0"
    expect that boxesList[1].header isEqualTo "Film #1"
    expect that boxesList[2].header isEqualTo "Muzyka #2"
    expect that boxesList[3].header isEqualTo "Ksiązka #3"
    expect that boxesList[4].header isEqualTo "Film #4"
    expect that boxesList[5].header isEqualTo "Muzyka #5"
  }
}

