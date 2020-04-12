object chapter7_7_8_3 {
  // 7.8.3.1 Implicit Class Conversion
  // Any implicit class can be reimplemented as a class paired with an implicit
  // method. Re-implement the IntOps class from the type enrichment section
  // in this way. Verify that the class still works the same way as it did before.

  class IntImplicits(number: Int) {
    def times(f: Int => Unit): Unit =
      for {
        x <- 0 until number
      } f(x)

    def yeah(): Unit =
      times(_ => println("Oh yeah!"))
  }

  implicit def intToIntImplicits(value: Int) =
    new IntImplicits(value)

  3.times(i => println(s"Look - it's the number $i!"))
  3.yeah()
}

chapter7_7_8_3
