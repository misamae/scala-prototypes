/**
  * Created by meisam on 10/05/2016.
  */

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}


object FuturesPrototype  {

  val futureSomething: Future[String] = Future {
    "testing"
  }

  val futureFailure = Future { 2/ 0}

  def main(args: Array[String]) {

    futureSomething.onComplete {
      case Failure(e) => println(e)
      case Success(t) => println(t)
    }

//    this null pointer exception never happens because of the partial function thing I guess
    futureFailure.onFailure( {
      case npe: NullPointerException => println("I'd be amazed if this prints out")
      case e => println(s"this prints out ?$e")
    })

    println(s"future completed? ${futureSomething.isCompleted}")
  }
}
