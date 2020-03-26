object exercise_5_5_4_4 {
  sealed trait Maybe[A]
  final case class Full[A](value: A) extends Maybe[A]
  final case class Empty[A]() extends Maybe[A]

  //5.4.4.1 Exercise: Maybe that Was a Mistake
  // create a generic trait called Maybe of a generic type A with two subtypes, Full
  // containing an A, and Empty containing no value. Example usage:
  val perhaps: Maybe[Int] = Empty[Int]
  val perhaps2: Maybe[Int] = Full(1)
  println(perhaps)
  println(perhaps2)
}

exercise_5_5_4_4
