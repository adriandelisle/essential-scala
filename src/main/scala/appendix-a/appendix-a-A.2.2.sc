object appendix_a_A_2_2 {

  // A.2.2.1 Positive Matches
  // Custom extractors allow us to abstract away complicated conditionals. In this
  // example we will build a very simple extractor, which we probably wouldn’t use
  // in real code, but which is representative of this idea.
  // Create an extractor Positive that matches any positive integer. Some test
  // cases:
  object Positive {
    def unapply(value: Int): Option[Int] =
      if (value > 0) Some(value) else None
  }

  assert(
    "No" ==
      (0 match {
        case Positive(_) => "Yes"
        case _ => "No"
      })
  )
  assert(
    "Yes" ==
      (42 match {
        case Positive(_) => "Yes"
        case _ => "No"
      })
  )

  // A.2.2.2 Titlecase extractor
  // Extractors can also transform their input. In this exercise we’ll write an extractor that converts any
  // string to titlecase by uppercasing the first letter of every word.
  // A test case:
  assert(
    "Sir Lord Doctor David Gurnell" ==
      ("sir lord doctor david gurnell" match {
        case Titlecase(str) => str
      })
  )

  // Tips:
  //  • Java Strings have the methods split(String), toUpperCase and substring(Int, Int).
  //  • The method split(String) returns a Java Array[String]. You can convert this to a
  //    List[String] using array.toList so you can map over it and manipulate each word.
  //  • A List[String] can be converted back to a String with the code list.mkString(" ").
  object Titlecase {
    def unapply(str: String): Option[String] = {
      Some(
        str.split(" ").toList.map{
          word => s"${word.substring(0, 1).toUpperCase}${word.substring(1)}"
        }.mkString(" ")
      )
    }
  }

}

appendix_a_A_2_2
