import play.api.libs.json.{Json, OFormat}

/**
  * Created by meisam on 14/04/2016.
  */

case class Document(reference: String, from: String, to: String, date: String)

object JsValuePrototype {

//  val jsValue: JsValue

  val formatter: OFormat[Document] = Json.format[Document]

}
