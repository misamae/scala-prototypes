import org.joda.time.{DateTime, DateTimeZone}
import org.scalatest.FlatSpec

object TimeSlot {

  def getTimeSlotTimestamp(timestamp: Long, hourSlotSize: Int): Long = {

    val dateTime = new DateTime(timestamp, DateTimeZone.UTC)

    new DateTime(dateTime.year().get,
      dateTime.monthOfYear().get,
      dateTime.dayOfMonth().get,
      dateTime.hourOfDay().get,
      (dateTime.minuteOfHour().get / hourSlotSize) * hourSlotSize, DateTimeZone.UTC)
      .getMillis
  }

}

class TimeSlotsSpec extends FlatSpec {

  "Beginning of the hour" should
    "0 for minutes" in {
    val timestamp = new DateTime(2016, 10, 10, 8, 0, DateTimeZone.UTC).getMillis

    val convertedTimestamp = TimeSlot.getTimeSlotTimestamp(timestamp, 10)

    assert(convertedTimestamp == timestamp)
  }

  "1 minute pass the hour" should "0 for minute" in {
    val expected = new DateTime(2016, 10, 10, 8, 0, DateTimeZone.UTC).getMillis

    val timestamp = new DateTime(2016, 10, 10, 8, 1, DateTimeZone.UTC).getMillis

    val convertedTimestamp = TimeSlot.getTimeSlotTimestamp(timestamp, 10)

    assert(convertedTimestamp == expected)
  }

  "10 minute pass the hour" should "10 for minute" in {
    val expected = new DateTime(2016, 10, 10, 8, 10, DateTimeZone.UTC).getMillis

    val timestamp = new DateTime(2016, 10, 10, 8, 10, DateTimeZone.UTC).getMillis

    val convertedTimestamp = TimeSlot.getTimeSlotTimestamp(timestamp, 10)

    println(new DateTime(expected, DateTimeZone.UTC))
    println(new DateTime(convertedTimestamp, DateTimeZone.UTC))

    assert(convertedTimestamp == expected)
  }
  "59 minute pass the hour" should "50 for minute" in {
    val expected = new DateTime(2016, 10, 10, 8, 50, DateTimeZone.UTC).getMillis

    val timestamp = new DateTime(2016, 10, 10, 8, 59, DateTimeZone.UTC).getMillis

    val convertedTimestamp = TimeSlot.getTimeSlotTimestamp(timestamp, 10)

    println(new DateTime(expected, DateTimeZone.UTC))
    println(new DateTime(convertedTimestamp, DateTimeZone.UTC))

    assert(convertedTimestamp == expected)
  }

}
