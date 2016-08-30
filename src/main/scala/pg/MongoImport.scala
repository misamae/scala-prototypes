package pg

import java.io.File

/**
  * Created by Meisam on 28/08/2016.
  */

case class VenusLastLog(id: Int
                       , imei: String
                       , type01: Int
                       , param: String
                       , lac: Int
                       , cid: Int
                       , satDateTime: String
                       , validity: Boolean
                       , geoLocation: String
                       , speed: Float
                       , bearing: Float
                       , address: String
                       , addedDate: String
                       , counter: Int
                       , alarm: Boolean)
object MongoImport {

  def main(args: Array[String]) {
    val sourceFilePath = "F:\\sarmad-backup\\tracking data\\venus-last-log.rpt"

    val fileContent: List[String] = scala.io.Source.fromFile(new File(sourceFilePath)).getLines().toList

    val header :: content01 = fileContent
    val separator :: content = content01

//    println(s"header: $header")
//    println(separator)

    for(l <- content) {
//      println(l)
      val items: Array[String] = l.split("\t", -1)

      println(items.size)

    }
  }
}
