object chapter6_6_6_2 {
  // 6.6.2.1 Adding All the Things ++
  // We’ve already seen how we can use a for comprehension to neatly add together three optional values.
  // Let’s extend this to other monads. Use the following definitions:
  import scala.util.Try

  val opt1 = Some(1)
  val opt2 = Some(2)
  val opt3 = Some(3)

  val seq1 = Seq(1)
  val seq2 = Seq(2)
  val seq3 = Seq(3)

  val try1 = Try(1)
  val try2 = Try(2)
  val try3 = Try(3)

  // Add together all the options to create a new option. Add together all the
  // sequences to create a new sequence. Add together all the trys to create a
  // new try. Use a for comprehension for each. It shouldn’t take you long!
  val options = for {
    a <- opt1
    b <- opt2
    c <- opt3
  } yield a + b + c
  println(options)

  val sequences = for {
    a <- seq1
    b <- seq2
    c <- seq3
  } yield a + b + c
  println(sequences)

  val tries = for {
    a <- try1
    b <- try2
    c <- try3
  } yield a + b + c
  println(tries)
}

chapter6_6_6_2
