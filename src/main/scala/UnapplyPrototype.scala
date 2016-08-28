/**
  * Created by meisam on 14/04/2016.
  */

object Twice {

  def apply(n: Int): Int = 2 * 2

  def unapply(n: Int): Option[Int] = if(n %2 == 0)Some(n/2) else None

}

object UnapplyPrototype {

  def main(args: Array[String]) {
    println(Twice(2))

    val x = Twice(2)

    x match {
      case Twice(n) => println(n)
    }
  }

}
