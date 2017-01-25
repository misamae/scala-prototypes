package tracking

import java.io.{File, FileWriter}

object GenerateSamples {

  val path = "/Users/meisam/code/shipping/eventsSample/events 20160914"
  val destinationPath = "/Users/meisam/code/shipping/shippingApi/tmp/samples-863070017487637"

//  val sampleImei = "861074020263672"
  val sampleImei = "863070017487637"

  def main(args: Array[String]): Unit = {
    val lines = scala.io.Source.fromFile(new File(path))
      .getLines()
      .filter(p => p.contains(sampleImei))
      .take(10000)

    val target = new File(destinationPath)
    val writer = new FileWriter(target)
    for(l <- lines) {
//      println(l)
      writer.write(l)
      writer.write('\n')
    }

    writer.close()

  }

}
