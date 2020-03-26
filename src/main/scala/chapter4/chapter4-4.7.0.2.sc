object exercise_4_4_7_0_2 {

  // 4.7.0.2 JSON
  // In the calculator exercise we gave you the algebraic data type representation.
  // In this exercise we want you to design the algebraic data type yourself. We’re
  // going to work in what is hopefully a familiar domain: JSON.
  // Design an algebraic data type to represent JSON. Don’t go directly to code.
  // Start by sketching out the design in terms of logical ands and ors—the building
  // blocks of algebraic data types. You might find it useful to use a notation similar
  // to BNF. For example, we could represent the Expression data type from the
  // previous exercise as follows:
  // Expression ::= Addition left:Expression right:Expression
  // | Subtraction left:Expression right:Expression
  // | Division left:Expression right:Expression
  // | SquareRoot value:Expression
  // | Number value:Int
  // This simplified notation allows us to concentrate on the structure of the algebraic data type without worrying
  // about the intricacies of Scala syntax.
  // Note you’ll need a sequence type to model JSON, and we haven’t looked at
  // Scala’s collection library yet. However we have seen how to implement a list
  // as an algebraic data type.
  // Here are some examples of JSON you’ll need to be able to represent
  // ["a string", 1.0, true]
  // {
  //  "a": [1,2,3],
  //  "b": ["a","b","c"]
  //  "c": { "doh":true, "ray":false, "me":1 }
  // }

  // Json ::= JsNumber value:Double
  //        | JsString value:String
  //        | JsBoolean value:Boolean
  //        | JsNull
  //        | JsSequence
  //        | JsObject
  // JsSequence ::= SeqCell head:Json tail:JsSequence
  //             | SeqEnd
  // JsObject ::= ObjectCell key:String value:Json tail:JsObject
  //            | ObjectEnd

  // Translate your representation to Scala code.
  sealed trait Json {
    def print: String = {

      def quote(string: String): String = s"${'"'}$string${'"'}"

      def sequenceToJsonString(seqCell: SeqCell): String =
        seqCell match {
          case SeqCell(head, tail@SeqCell(_, _)) => s"${head.print}, ${sequenceToJsonString(tail)}"
          case SeqCell(head, SeqEnd) => head.print
        }

      def jsObjectToJsonString(objectCell: ObjectCell): String =
        objectCell match {
          case ObjectCell(key, value, tail @ ObjectCell(_, _, _)) => s"${quote(key)}: ${value.print}, ${jsObjectToJsonString(tail)}"
          case ObjectCell(key, value, ObjectEnd) => s"${quote(key)}: ${value.print}"
        }

      this match {
        case JsNumber(value) => s"${value}"
        case JsString(value) => quote(value)
        case JsBoolean(value) => s"${value}"
        case JsNull => "null"
        case sequence @ SeqCell(_, _) => s"[${sequenceToJsonString(sequence)}]"
        case SeqEnd => "[]"
        case jsObject @ ObjectCell(_, _, _) => s"{${jsObjectToJsonString(jsObject)}}"
        case ObjectEnd => "{}"
      }
    }
  }

  final case class JsNumber(value: Double) extends Json

  final case class JsString(value: String) extends Json

  final case class JsBoolean(value: Boolean) extends Json

  final case object JsNull extends Json

  sealed trait JsSequence extends Json

  final case class SeqCell(head: Json, tail: JsSequence) extends JsSequence

  final case object SeqEnd extends JsSequence

  sealed trait JsObject extends Json

  final case class ObjectCell(key: String, value: Json, tail: JsObject) extends JsObject

  final case object ObjectEnd extends JsObject

  // Now add a method to convert your JSON representation to a String. Make
  // sure you enclose strings in quotes, and handle arrays and objects properly.

  // Test your method works. Here are some examples using the representation I
  // chose.

  println(JsNumber(1.0).print)
  println(JsString("Wow").print)
  println(JsBoolean(true).print)
  println(SeqEnd.print)
  println(SeqCell(JsString("a string"), SeqCell(JsNumber(1.0), SeqCell(JsBoolean(true), SeqEnd))).print)
  // res0: String = ["a string", 1.0, true]
  println(ObjectEnd)
  println(ObjectCell(
    "a", SeqCell(JsNumber(1.0), SeqCell(JsNumber(2.0), SeqCell(JsNumber
    (3.0), SeqEnd))),
    ObjectCell(
      "b", SeqCell(JsString("a"), SeqCell(JsString("b"), SeqCell(
        JsString("c"), SeqEnd))),
      ObjectCell(
        "c", ObjectCell("doh", JsBoolean(true),
          ObjectCell("ray", JsBoolean(false),
            ObjectCell("me", JsNumber(1.0), ObjectEnd))),
        ObjectEnd
      )
    )
  ).print)
  // res1: String = {"a": [1.0, 2.0, 3.0], "b": ["a", "b", "c"], "c": {"doh": true, "ray": false, "me": 1.0}}
}

exercise_4_4_7_0_2
