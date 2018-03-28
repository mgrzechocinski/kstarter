package me.grzechocinski.kstarter.domain

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

/**
 * @param isoDate SystemDate in ISO-8601 format, i.e. yyyy-MM-dd
 */
class SystemDate(isoDate: String) {

  val value: LocalDate = LocalDate.parse(isoDate, DateTimeFormatter.ISO_DATE)

  override fun toString(): String {
    return value.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
  }

  fun isBefore(other: SystemDate) = value.isBefore(other.value)

  fun isAfter(other: SystemDate) = value.isAfter(other.value)

  operator fun rangeTo(endDate: SystemDate): SystemDateRange = SystemDateRange(this, endDate)
}

class SystemDateRange(private val start: SystemDate, private val end: SystemDate) {

  operator fun contains(yearMiddle: SystemDate): Boolean {
    return !yearMiddle.isBefore(start) && !yearMiddle.isAfter(end)
  }
}