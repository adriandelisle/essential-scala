object exercise_5_4_1 {
  // 5.4.1.1 Exercise: Pairs
  // Implement the Pair class from above. It should store two values—one and
  // two—and be generic in both arguments. Example usage:
  case class Pair[A, B](one: A, two: B)
  val pair = Pair[String, Int]("hi", 2)
  // // pair: Pair[String,Int] = Pair(hi,2)
  println(pair.one)
  //// res0: String = hi
  println(pair.two)
  //// res1: Int = 2


}

exercise_5_4_1
