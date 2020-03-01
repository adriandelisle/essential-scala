// 4.4.4.1 Stop on a Dime
// A traffic light is red, green, or yellow. Translate this description into Scala code.
sealed trait TrafficLight
case object Red extends TrafficLight
case object Green extends TrafficLight
case object Yellow extends TrafficLight

// 4.4.4.2 Calculator
// A calculation may succeed (with an Int result) or fail (with a String message). Implement this.
sealed trait Calculation
final case class Success(result: Int) extends Calculation
final case class Fail(message: String) extends Calculation

// 4.4.4.3 Water, Water, Everywhere
// Bottled water has a size (an Int), a source (which is a well, spring, or tap), and
// a Boolean carbonated. Implement this in Scala.
sealed trait Source
case object Well extends Source
case object Spring extends Source
case object Tap extends Source
final case class BottledWater(size: Int, source: Source, carbonated: Boolean)
