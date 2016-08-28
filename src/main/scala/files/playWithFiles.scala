package files

import java.io.InputStreamReader
import scala.collection.mutable.ArrayBuffer
import scala.collection.JavaConverters

/**
  * Created by meisam on 15/08/2016.
  */
object playWithFiles {

  def main(args: Array[String]): Unit = {
    val source = getClass.getResourceAsStream("sample.txt")
    val reader = new InputStreamReader(source)
    var lenght = 0
//    var buffer = ArrayBuffer()
//    while((lenght = reader.read(buffer)))

  }

}
