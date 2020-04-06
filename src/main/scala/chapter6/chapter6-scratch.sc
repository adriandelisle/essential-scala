object chapter6_scratch {
  val sequence = Seq(1, 2, 3)
  println(sequence)
  println(sequence.apply(2))
  println(Nil)
  val list = 4 :: 5 :: Nil
  println(list)

  import scala.collection.immutable.Vector
  println(Vector(1, 2, 3))

  val thing = for {
    x <- Seq(1, 2, 3)
  } yield x * 2
  println(thing)
}

chapter6_scratch
