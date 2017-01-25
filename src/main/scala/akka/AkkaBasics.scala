package akka

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Keep, RunnableGraph, Sink, Source}

import scala.concurrent.Future

object AkkaBasics {

  implicit val system = ActorSystem("test")
  implicit val materializer = ActorMaterializer()

  val source = Source(1 to 10)

  val sink = Sink.fold[Int, Int](0)(_ + _)

  val runnable = source.toMat(sink)(Keep.right)

  val sum: Future[Int] = runnable.run()

  println(sum)

  val sum01 = source.runWith(sink)
  println(sum01)

  def main(args: Array[String]): Unit = {

    Thread.sleep(500)
    system.terminate()
  }

}
