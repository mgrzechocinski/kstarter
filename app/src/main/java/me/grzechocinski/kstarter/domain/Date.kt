package me.grzechocinski.kstarter.domain

import java.text.SimpleDateFormat
import java.util.*
import java.util.Date as JavaDate

class Date(dateToParse: String) {

  val value: JavaDate = SimpleDateFormat("YYYY-mm-DD", Locale("pl")).parse(dateToParse)

  override fun toString(): String {
    return value.toString()
  }
}
