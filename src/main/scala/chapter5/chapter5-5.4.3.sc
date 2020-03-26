object exercise_5_4_3_1 {

  sealed trait Sum[A, B]

  final case class Left[A, B](value: A) extends Sum[A, B]

  final case class Right[A, B](value: B) extends Sum[A, B]

  // 5.4.3.1 Exercise: Generic Sum Type
  // Implement a trait Sum[A, B] with two subtypes Left and Right. Create type
  // parameters so that Left and Right can wrap up values of two different types.
  // Hint: you will need to put both type parameters on all three types. Example
  // usage:
  println(Left[Int, String](1).value)
  // res9: Int = 1
  println(Right[Int, String]("foo").value)
  // res10: String = foo
  val sum: Sum[Int, String] = Right("foo")
  println(sum)
  // sum: sum.Sum[Int,String] = Right(foo)
  sum match {
    case Left(x) => x.toString
    case Right(x) => x
  }
  // res11: String = foo
}

exercise_5_4_3_1
