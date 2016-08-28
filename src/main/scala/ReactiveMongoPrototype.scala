import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.{DefaultDB, MongoConnection, MongoDriver, collections}
import reactivemongo.bson.Macros
import reactivemongo.bson.BSONDocumentWriter
import reactivemongo.bson.BSONDocumentReader

import scala.concurrent.Future
import scala.concurrent.ExecutionContext

/**
  * Created by meisam on 07/08/2016.
  */
object ReactiveMongoPrototype {

  import ExecutionContext.Implicits.global

  case class Person(name: String)

  def main(args: Array[String]): Unit = {
    val connectionString = "mongodb://localhost:27017/shipping_dev"

    val driver = MongoDriver()
    val parsedUrl = MongoConnection.parseURI(connectionString)

    val connection = parsedUrl.map(driver.connection)

    val futureConnection = Future.fromTry(connection)

    val db: Future[DefaultDB] = futureConnection.flatMap(_.database("people_dev"))

    assert(db != null)

//    implicit val collectionProducer = collections.bson.BSONCollectionProducer

    val people: Future[BSONCollection] = db.map(_.collection("people"))

    implicit def writer = Macros.writer[Person]

    people.flatMap(c => c.insert(Person("Meisam")).map { _ => {}})

  }


}
