object exercise_5_5_6_6 {

  // 5.6.6.1 Covariance and Contravariance
  // Using the notation A <: B to indicate A is a subtype of B and assuming:
  // • Siamese <: Cat <: Animal; and
  // • Purr <: CatSound <: Sound
  // if I have a method
  // def groom(groomer: Cat => CatSound): CatSound = {
  //   val oswald = Cat("Black", "Cat food")
  //   groomer(oswald)
  // }
  // which of the following can I pass to groom?
  // • A function of type Animal => Purr
  //   yes
  // • A function of type Siamese => Purr
  //   no, oswald is not siamese
  // • A function of type Animal => Sound
  //   no, because the return type is CatSound

  // 5.6.6.2 Calculator Again
  // We’re going to return to the interpreter example we saw at the end of the last
  // chapter. This time we’re going to use the general abstractions we’ve created
  // in this chapter, and our new knowledge of map, flatMap, and fold.
  // We’re going to represent calculations as Sum[String, Double], where the
  // String is an error message. Extend Sum to have map and fold method.
  sealed trait Sum[+A, +B] {
    def fold[C](error: A => C, success: B => C): C = {
      this match {
        case Failure(value) => error(value)
        case Success(value) => success(value)
      }
    }

    def map[C](f: B => C): Sum[A, C] = {
      this match {
        case Failure(value) => Failure(value)
        case Success(value) => Success(f(value))
      }
    }

    def flatMap[AA >: A, C](f: B => Sum[AA, C]): Sum[AA, C] =
      this match {
        case Failure(v) => Failure(v)
        case Success(v) => f(v)
      }
  }

  final case class Failure[A](value: A) extends Sum[A, Nothing]

  final case class Success[B](value: B) extends Sum[Nothing, B]

  // Now we’re going to reimplement the calculator from last time. We have an
  // abstract syntax tree defined via the following algebraic data type:
  sealed trait Expression {
    def eval: Sum[String, Double] = {
      this match {
        case Addition(left, right) => left.eval.flatMap(l => right.eval.flatMap(r => Success(l + r)))
        case Subtraction(left, right) => left.eval.flatMap(l => right.eval.flatMap(r => Success(l - r)))
        case Division(left, right) => left.eval.flatMap(
          l => right.eval.flatMap(
            r => if (r == 0) Failure("Division by zero") else Success(l / r)
          )
        )
        case SquareRoot(value) => value.eval.flatMap(
          v => if (v < 0) Failure("Square root of negative number") else Success(scala.math.sqrt(v))
        )
        case Number(value) => Success(value)
      }
    }
  }

  final case class Addition(left: Expression, right: Expression) extends Expression

  final case class Subtraction(left: Expression, right: Expression) extends Expression

  final case class Division(left: Expression, right: Expression) extends Expression

  final case class SquareRoot(value: Expression) extends Expression

  final case class Number(value: Double) extends Expression

  // Now implement a method eval: Sum[String, Double] on Expression.
  // Use flatMap and map on Sum and introduce any utility methods you see fit to
  // make the code more compact. Here are some test cases:


  assert(Addition(Number(1), Number(2)).eval == Success(3))
  assert(SquareRoot(Number(-1)).eval == Failure("Square root of negative number"))
  assert(Division(Number(4), Number(0)).eval == Failure("Division by zero"))
  assert(Division(Addition(Subtraction(Number(8), Number(6)), Number(2)), Number(2)).eval == Success(2.0))
}

exercise_5_5_6_6
