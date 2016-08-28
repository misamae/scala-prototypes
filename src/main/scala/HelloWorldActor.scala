import akka.actor.{Actor, Props}

/**
  * Created by meisam on 27/03/2016.
  */
class HelloWorldActor extends Actor {
  override def preStart() = {
    println("pre start in HelloWorld Actor")

    val sillyActor = context.actorOf(Props[SillyActor], "sillyActor")
    val seriousActor = context.actorOf(Props[SeriousActor], "seriousActor")
    val intActor = context.actorOf(Props[IntActor], "intActor")

    intActor ! 234
    intActor ! 56789
    intActor ! "fucking text"

    sillyActor ! "Fucking hello for the fucking world"

    seriousActor ! "test how serious you are!"
  }

  override def receive = {
    case x => {
      println(s"$x")
      context.stop(self)
    }
  }
}
