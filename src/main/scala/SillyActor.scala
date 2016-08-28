import akka.actor.Actor

/**
  * Created by meisam on 27/03/2016.
  */
class SillyActor extends Actor {
  override def preStart() = {
    println("SillyActor preStart")
  }

  override def receive: Receive = {
    case x => println(s"testing $x")
  }
}

class SeriousActor extends Actor {
  override def receive = {
    case x => {
      println("to be or not to be")

      sender() ! s"go fuck yourself ($x)"
    }
  }
}




