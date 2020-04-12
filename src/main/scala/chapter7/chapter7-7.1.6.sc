object chapter7_7_1_6 {

  // 7.1.6.1 More Orderings
  // Define an Ordering that orders Ints from lowest to highest by absolute value.
  // The following test cases should pass.
  implicit val absOrdering: Ordering[Int] = Ordering.fromLessThan((a: Int, b: Int) => scala.math.abs(a) < scala.math.abs(b))
  println("7.1.6.1 More Orderings")
  println(List(-4, -1, 0, 2, 3))
  println(List(-4, -1, 0, 2, 3).sorted(absOrdering))
  assert(List(-4, -1, 0, 2, 3).sorted(absOrdering) == List(0, -1, 2, 3, -4))
  assert(List(-4, -3, -2, -1).sorted(absOrdering) == List(-1, -2, -3, -4))

  // Now make your ordering an implicit value, so the following test cases work.
  assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))
  assert(List(-4, -3, -2, -1).sorted == List(-1, -2, -3, -4))


  // 7.1.6.2 Rational Orderings
  // Scala doesn’t have a class to represent rational numbers, but we can easily
  // implement one ourselves.
  final case class Rational(numerator: Int, denominator: Int)

  // Implement an Ordering for Rational to order rationals from smallest to
  // largest. The following test case should pass.
  implicit val rationalOrdering: Ordering[Rational] = Ordering.fromLessThan{ (a: Rational, b: Rational) =>
    a.numerator * b.denominator < b.numerator * a.denominator
  }
  println("7.1.6.2 Rational Orderings")
  println(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)))
  println(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted)
  assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted == List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))
}

chapter7_7_1_6
