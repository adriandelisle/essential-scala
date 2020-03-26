object exercise_4_4_7_0_1 {

  // 4.7.0.1 A Calculator
  // In this exercise we’ll implement a simple interpreter for programs containing only numeric operations.
  // We start by defining some types to represent the expressions we’ll be operating on. In the compiler literature this is known as an abstract syntax tree.
  // Our representation is:
  // • An Expression is an Addition, Subtraction, or a Number;
  // • An Addition has a left and right Expression;
  // • A Subtraction has a left and right Expression; or
  // • A Number has a value of type Double.
  // Implement this in Scala.

  sealed trait Expression {
    def eval: Calculation =
      this match {
        case Addition(left, right) =>
          left.eval match {
            case Success(resultLeft) =>
              right.eval match {
                case Success(resultRight) => Success(resultLeft + resultRight)
                case Failure(reason) => Failure(reason)
              }
            case Failure(reason) => Failure(reason)
          }
        case Subtraction(left, right) =>
          left.eval match {
            case Success(resultLeft) =>
              right.eval match {
                case Success(resultRight) => Success(resultLeft - resultRight)
                case Failure(reason) => Failure(reason)
              }
            case Failure(reason) => Failure(reason)
          }
        case Division(numerator, denominator) =>
          numerator.eval match {
            case Success(resultLeft) => denominator.eval match {
              case Success(resultRight) => resultRight match {
                case 0 => Failure("Division by zero")
                case _ => Success(resultLeft / resultRight)
              }
              case Failure(reason) => Failure(reason)
            }
            case Failure(reason) => Failure(reason)
          }
        case SquareRoot(value) =>
          value.eval match {
            case Success(result) =>
              if (result < 0) Failure("Square root of negative number") else Success(math.sqrt(result))
            case Failure(reason) => Failure(reason)
          }
        case Number(value) => Success(value)
      }
  }

  final case class Addition(left: Expression, right: Expression) extends Expression

  final case class Subtraction(left: Expression, right: Expression) extends Expression

  final case class Number(value: Double) extends Expression

  // Now implement a method eval that converts an Expression to a Double.
  // Use polymorphism or pattern matching as you see fit. Explain your choice of
  // implementation method.

  // Pattern matching seems cleaner to me
  assert(Addition(Number(1.0), Number(5.0)).eval == Success(6.0))
  assert(Subtraction(Number(-1.0), Number(1.0)).eval == Success(-2.0))

  // We’re now going to add some expressions that call fail: division and square
  // root. Start by extending the abstract syntax tree to include representations
  // for Division and SquareRoot.

  final case class Division(numerator: Expression, denominator: Expression) extends Expression

  final case class SquareRoot(value: Expression) extends Expression

  // Now we’re going to change eval to represent that a computation can fail.
  // (Double uses NaN to indicate a computation failed, but we want to be helpful
  // to the user and tell them why the computation failed.) Implement an appropriate algebraic data type.

  sealed trait Calculation

  final case class Success(result: Double) extends Calculation

  final case class Failure(reason: String) extends Calculation

  // Now change eval to return your result type, which I have called Calculation
  // in my implementation. Here are some examples:
  assert(Addition(SquareRoot(Number(-1.0)), Number(2.0)).eval == Failure("Square root of negative number"))
  assert(Addition(SquareRoot(Number(4.0)), Number(2.0)).eval == Success(4.0))
  assert(Division(Number(4), Number(0)).eval == Failure("Division by zero"))

}

exercise_4_4_7_0_1
