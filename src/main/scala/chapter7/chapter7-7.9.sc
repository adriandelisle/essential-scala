object chapter7_7_9 {

  sealed trait JsValue {
    def stringify: String
  }

  final case class JsObject(values: Map[String, JsValue]) extends
    JsValue {
    def stringify = values
      .map { case (name, value) => "\"" + name + "\":" + value.stringify
      }
      .mkString("{", ",", "}")
  }

  final case class JsString(value: String) extends JsValue {
    def stringify = "\"" + value.replaceAll("\\|\"", "\\\\$1") + "\""
  }

  // 7.9.1 Convert X to JSON
  // Let’s create a type class for converting Scala data to JSON. Implement a
  // JsWriter trait containing a single abstract method write that converts a
  // value to a JsValue.
  trait JsWriter[A] {
    def write(value: A): JsValue
  }

  // Now let’s create the dispatch part of our type class. Write a JsUtil object
  // containing a single method toJson. The method should accept a value of an
  // arbitrary type A and convert it to JSON.
  // Tip: your method will have to accept an implicit JsWriter to do the actual
  // conversion.
  object JsUtil {
    def toJson[A](value: A)(implicit jsWriter: JsWriter[A]) =
      jsWriter.write(value)
  }

  // Now, let’s revisit our data types from the web site visitors example in the
  // Sealed traits section:

  import java.util.Date

  sealed trait Visitor {
    def id: String

    def createdAt: Date

    def age: Long = new Date().getTime() - createdAt.getTime()
  }

  final case class Anonymous(id: String, createdAt: Date = new Date()) extends Visitor

  final case class User(id: String, email: String, createdAt: Date = new Date()) extends Visitor

  // Write JsWriter instances for Anonymous and User.

  implicit object AnonymousWriter extends JsWriter[Anonymous] {
    def write(value: Anonymous) = JsObject(Map[String, JsValue](
      "id" -> JsString(value.id),
      "createdAt" -> JsString(value.createdAt.toString)
    ))
  }

  implicit object User extends JsWriter[User] {
    def write(value: User): JsValue = JsObject(
      Map[String, JsValue](
        "id" -> JsString(value.id),
        "email" -> JsString(value.email),
        "createdAt" -> JsString(value.createdAt.toString)
      )
    )
  }

  // Given these two definitions we can implement a JsWriter for Visitor as
  // follows. This uses a new type of pattern – a: B – which matches any value of
  // type B and binds it to a variable a:

  implicit object VisitorWriter extends JsWriter[Visitor] {
    def write(value: Visitor) = value match {
      case anon: Anonymous => JsUtil.toJson(anon)
      case user: User => JsUtil.toJson(user)
    }
  }

  val visitors: Seq[Visitor] = Seq(Anonymous("001", new Date), User("003", "dave@xample.com", new Date))

  println(visitors)
  val visitorsJson = visitors.map(visitor => JsUtil.toJson(visitor))
  println(visitorsJson)

  // 7.9.2 Prettier Conversion Syntax
  // Let’s improve our JSON syntax by combining type classes and type enrichment.
  // Convert JsUtil to an implicit class with a toJson method. Sample usage:

  implicit class JsUtil[A](value: A) {
    def toJson(implicit writer: JsWriter[A]) =
      writer.write(value)
  }

  println(Anonymous("001", new Date).toJson)
}

chapter7_7_9
