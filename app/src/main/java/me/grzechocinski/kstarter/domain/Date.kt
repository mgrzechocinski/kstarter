package me.grzechocinski.kstarter.domain

import java.text.SimpleDateFormat
import java.util.*
import java.util.Date as JavaDate

class Date(dateToParse: String) {

  val value: JavaDate = SimpleDateFormat("YYYY-mm-DD", Locale("pl")).parse(dateToParse)

  override fun toString(): String {
    return value.toString()
  }

  operator fun rangeTo(yearEnd: Date): DateRange = DateRange(this, yearEnd)

  class DateRange(private val yearStart: Date, private val yearEnd: Date) {

    operator fun contains(yearMiddle: Date): Boolean {
       return yearMiddle.value.before(yearEnd.value) &&
         yearMiddle.value.after(yearStart.value)
    }
  }
}
