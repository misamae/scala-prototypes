/**
  * Created by meisam on 11/05/2016.
  */
object OptionsPrototype {

  def main(args: Array[String]) {

    val o = Some("1234")
    val o1: Option[String] = None

    val v: Option[String] = o.map(s => s)
    val v1: Option[String] = o1.map(s => s)

    println(v)
    println(v1)

  }


}
