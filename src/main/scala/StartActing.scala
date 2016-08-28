/**
  * Created by meisam on 27/03/2016.
  */
object StartActing {
  def main(args: Array[String]) {
    akka.Main.main(Array(classOf[HelloWorldActor].getName))
  }
}
