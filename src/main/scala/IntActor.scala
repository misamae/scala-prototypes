import akka.actor.Actor
import akka.actor.Actor.Receive

/**
  * Created by meisam on 27/03/2016.
  */
class IntActor extends Actor {
  override def receive: Receive = {
    case x: Int => println(s"IntActor is only accepting ints for example $x")
  }
}
