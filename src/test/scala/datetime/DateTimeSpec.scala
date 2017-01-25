package datetime

import org.joda.time.DateTime
import org.scalatest.{FlatSpec, Matchers}

class DateTimeSpec extends FlatSpec with Matchers {

  "send from client" should "fix something" in {
    val t = 1479600000000L

    val dateTime = new DateTime(t)

    println(dateTime)

    println(new DateTime(1477090800000L))

    println(s"from: ${new DateTime(1477203526860L)} to ${new DateTime(1477289926860L)}")

    println(5 % 10)
  }

  "on history" should "be in range" in {
    println(s"from: ${new DateTime(1476656677000L)} to ${new DateTime(1477238690000L)}")
  }


}
