package pg

import org.json4s._
import org.json4s.jackson.JsonMethods._

/**
  * Created by Meisam on 29/08/2016.
  */
case class Truck(registrationNumber: String
                 , chassisNumber: String
                 , vehicleType: String
                 , description: String
                 , color: String
                 , mobileNumber: String
                 , imei: String
                 , trackerType: String)

object DriversImport {
  def main(args: Array[String]): Unit = {
//    val resource = this.getClass.getResourceAsStream("/trucks.rpt")
//
//    assert(resource != null)
//    val lines = scala.io.Source.fromInputStream(resource).getLines().toList

    val raw =
      """ { "registrationNumber": "584ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017487249", "mobileNum": "09300977435", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "541ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94پاسارگاز", "color": "سفید", "imei": "861074020288109", "mobileNum": "09300872375", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "772ع69 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017487637", "mobileNum": "09300933500", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "266ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "ارساگاز94", "color": "سفید", "imei": "861074020263672", "mobileNum": "09300864622", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "   235ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94", "color": "", "imei": "863070017354068", "mobileNum": "09300931867", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "268ع66 ایران44", "chassisNumber": "عباس بهشتی", "vehicleType": "كاميون", "description": "94", "color": "سفید", "imei": "861074020293810", "mobileNum": "09300916539", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "255ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94", "color": "سفید", "imei": "861074020287556", "mobileNum": "09300870328", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "211ع66 ایران44", "chassisNumber": "", "vehicleType": "كاميون", "description": "پاسارگاز94", "color": "سفید", "imei": "861074020283563", "mobileNum": "09300864602", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "262ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94 پارسا ترابر پرشیا", "color": "سفید", "imei": "863070017487512", "mobileNum": "09300884901", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "227ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017487728", "mobileNum": "09300952604", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "198ع66 ایران44", "chassisNumber": "", "vehicleType": "كاميون", "description": "پارسا ترابر پرشیامدل 94 ", "color": "سفید", "imei": "863070017355388", "mobileNum": "09300970646", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "256ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "پاسارگاز94", "color": "سفید", "imei": "861074020259563", "mobileNum": "09300864602", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "572ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "", "imei": "863070017489112", "mobileNum": "09300916530", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "571ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017489005", "mobileNum": "09300884919", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "247ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017353045", "mobileNum": "09300920197", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "263ع66 ایران44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94ارساگاز", "color": "سفید", "imei": "861074020293703", "mobileNum": "09300950642", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "793ع69 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94 ارساگاز", "color": "سفید", "imei": "863070017354134", "mobileNum": "09300879447", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "565ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017353169", "mobileNum": "09300945209", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "787ع69 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017354498", "mobileNum": "09300868144", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "254ع66 ایران44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94", "color": "سفید", "imei": "861074020284223", "mobileNum": "09300918275", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "237ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94پاسارگاز", "color": "سفید", "imei": "861074020284611", "mobileNum": "09300877827", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "798ع69 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070015457756", "mobileNum": "09300909120", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "267ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94", "color": "سفید", "imei": "863070017488569", "mobileNum": "09300970690", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "576ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "", "imei": "863070017489328", "mobileNum": "09300879502", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "251ع66 ایران44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017355032", "mobileNum": "09300890434", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "851ع22 ایران 33", "chassisNumber": "", "vehicleType": "كاميون", "description": "94پاسارگاز", "color": "سفید", "imei": "863070017489955", "mobileNum": "09300967714", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "244ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017487231", "mobileNum": "09300864325", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "581ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017355123", "mobileNum": "09300864407", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "714ع69 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017355206", "mobileNum": "09300928081", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "224ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94 شرکت پاسارگاز", "color": "سفید", "imei": "861074020293745", "mobileNum": "09300866407", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "243ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94پاسارگاز", "color": "سفید", "imei": "861074020288034", "mobileNum": "09300971967", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "563ع66  ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017489179", "mobileNum": "09300963940", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "549ع66 ایران44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94", "color": "سفید", "imei": "861074020260827", "mobileNum": "09300926153", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "566ع66  ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017487363", "mobileNum": "09300965815", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "248ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017488536", "mobileNum": "09300943056", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "556ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017354126", "mobileNum": "09300935570", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "__327ع16 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "کامیون ایسوزو - توزیع سیلندر", "color": "", "imei": "863070017489914", "mobileNum": "09300888387", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "222ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94", "color": "سفید", "imei": "861074020295393", "mobileNum": "09300894042", "trackerType": "JupiterProvider" }
        |{ "registrationNumber": "782ع69 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "", "color": "سفید", "imei": "863070017488494", "mobileNum": "09300977447", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "257ع66 ایران 44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94", "color": "سفید", "imei": "863070017488965", "mobileNum": "09300901579", "trackerType": "VenusProvider" }
        |{ "registrationNumber": "252ع66 ایران44", "chassisNumber": "", "vehicleType": "كاميون", "description": "94پاسارگاز", "color": "سفید", "imei": "861074020259530", "mobileNum": "09300928005", "trackerType": "JupiterProvider" }
      """.stripMargin

    val lines = raw.split('\n')

    for(l <- lines.filter(_.nonEmpty).map(_.trim).take(41)) {

      val parsed = parse(l)

      val registrationNumber = parsed \ "registrationNumber" \\ classOf[JString] head
      val chassisNumber = parsed \ "chassisNumber" \\ classOf[JString] head
      val vehicleType = parsed \ "vehicleType" \\ classOf[JString] head
      val description = parsed \ "description" \\ classOf[JString] head
      val color = parsed \ "color" \\ classOf[JString] head
      val imei = parsed \ "imei" \\ classOf[JString] head
      val mobileNum = parsed \ "mobileNum" \\ classOf[JString] head
      val trackerType = parsed \ "trackerType" \\ classOf[JString] head

      val truck = Truck(registrationNumber, chassisNumber, vehicleType, description, color, mobileNum, imei, trackerType)

      println(truck)
    }
  }
}
