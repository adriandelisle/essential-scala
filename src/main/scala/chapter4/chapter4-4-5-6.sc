// 4.5.6.1 Traffic Lights
// In the previous section we implemented a TrafficLight data type like so:
//  sealed trait TrafficLight
//  case object Red extends TrafficLight
//  case object Green extends TrafficLight
//  case object Yellow extends TrafficLight
// Using polymorphism and then using pattern matching implement a method
// called next which returns the next TrafficLight in the standard Red ->
// Green -> Yellow -> Red cycle. Do you think it is better to implement this
// method inside or outside the class? If inside, would you use pattern matching
// or polymorphism? Why?

// Better to implement inside since it depends on no external things.
// I'd use pattern matching simply because its easier to follow in this case.

// polymorphism
object polymorphism {

  sealed trait TrafficLightPoly {
    def next: TrafficLightPoly
  }

  case object YellowPoly extends TrafficLightPoly {
    def next: TrafficLightPoly = RedPoly
  }

  case object GreenPoly extends TrafficLightPoly {
    def next: TrafficLightPoly = YellowPoly
  }

  case object RedPoly extends TrafficLightPoly {
    def next: TrafficLightPoly = GreenPoly
  }

}

polymorphism

//
// pattern matching
object patternMatching {

  sealed trait TrafficLight {
    def next: TrafficLight =
      this match {
        case Red => Green
        case Green => Yellow
        case Yellow => Red
      }
  }

  case object Red extends TrafficLight

  case object Green extends TrafficLight

  case object Yellow extends TrafficLight

}

patternMatching

// 4.5.6.2 Calculation
// In the last section we created a Calculation data type like so:
// sealed trait Calculation
// final case class Success(result: Int) extends Calculation
// final case class Failure(reason: String) extends Calculation
// We’re now going to write some methods that use a Calculation to perform
// a larger calculation. These methods will have a somewhat unusual shape—this
// is a precursor to things we’ll be exploring soon—but if you follow the patterns
// you will be fine.

sealed trait Calculation

final case class Success(result: Int) extends Calculation

final case class Failure(reason: String) extends Calculation

object Calculator {
  def +(calculation: Calculation, n: Int): Calculation = {
    calculation match {
      case Success(result) => Success(result + n)
      case Failure(reason) => Failure(reason)
    }
  }

  def -(calculation: Calculation, n: Int): Calculation = {
    calculation match {
      case Success(result) => Success(result - n)
      case Failure(reason) => Failure(reason)
    }
  }

  def /(calculation: Calculation, n: Int): Calculation = {
    calculation match {
      case Success(result) => n match {
        case 0 => Failure("Division by zero")
        case _ => Success(result / n)
      }
      case Failure(reason) => Failure(reason)
    }
  }
}

// Create a Calculator object. On Calculator define methods + and - that
// accept a Calculation and an Int, and return a new Calculation. Here are
// some examples
assert(Calculator.+(Success(1), 1) == Success(2))
assert(Calculator.-(Success(1), 1) == Success(0))
assert(Calculator.+(Failure("Badness"), 1) == Failure("Badness"))

// Now write a division method that fails if the divisor is 0. The following tests
// should pass. Note the behavior for the last test. This indicates “fail fast” behavior. If a calculation has already failed we keep that failure and don’t process
//any more data even if, as is the case in the test, doing so would lead to another
//failure.
assert(Calculator./(Success(4), 2) == Success(2))
assert(Calculator./(Success(4), 0) == Failure("Division by zero"))
assert(Calculator./(Failure("Badness"), 0) == Failure("Badness"))

// 4.5.6.3 Email
// Recall the Visitor trait we looked at earlier: a website Visitor is either
// Anonymous or a signed-in User. Now imagine we wanted to add the ability
// to send emails to visitors. We can only email signed-in users, and sending an
// email requires a lot of knowledge about SMTP settings, MIME headers, and
// so on. Would an email method be better implemented using polymorphism
// on the Visitor trait or using pattern matching in an EmailService object?
// Why?

// It would be better in an EmailService object since it depends on so many things unrelated to the visitor trait
// SMTP settings, MIME headers, etc
