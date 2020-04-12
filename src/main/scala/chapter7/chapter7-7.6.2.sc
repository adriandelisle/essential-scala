object chapter7_7_6_2 {
  // 7.6.2.1 Drinking the Kool Aid
  // Use your newfound powers to add a method yeah to Int, which prints Oh
  // yeah! as many times as the Int on which it is called if the Int is positive, and
  // is silent otherwise. Here’s an example of usage:
  println("7.6.2.1 Drinking the Kool Aid")
  2.yeah()
  3.yeah()
  -1.yeah()

  implicit class IntImplicits(number: Int) {
    def yeah(): Unit = {
      println(s"number: $number")
      for {
        x <- 0 until number
      } println("Oh yeah!")
    }

    def times(f: Int => Unit): Unit =
      for {
        x <- 0 until number
      } f(x)

    def yeah2(): Unit =
      times(_ => println("Oh yeah!"))
  }

  // When you have written your implicit class, package it in an IntImplicits object.

  // 7.6.2.2 Times
  // Extend your previous example to give Int an extra method called times that
  // accepts a function of type Int => Unit as an argument and executes it n
  // times. Example usage:
  println("7.6.2.2 Times")
  3.times(i => println(s"Look - it's the number $i!"))
  3.yeah2()
  // For bonus points, re-implement yeah in terms of times

  // 7.6.3 Easy Equality
  // Recall our Equal type class from a previous section.
  println("7.6.3 Easy Equality")
  trait Equal[A] {
    def equal(v1: A, v2: A): Boolean
  }

  implicit object caseInsensitiveEqual extends Equal[String] {
    def equal(v1: String, v2: String): Boolean =
      v1.toLowerCase == v2.toLowerCase
  }
  // Implement an enrichment so we can use this type class via a triple equal (===)
  // method. For example, if the correct implicits are in scope the following should
  // work.
  implicit class stringOps(str: String) {
    def ===(strB: String)(implicit equal: Equal[String]) =
      equal.equal(str, strB)
  }
  println("abcd".===("ABCD")) // Assumes case-insensitive equality implicit
}

chapter7_7_6_2
