// 4.1.4 Exercises
// 4.1.4.1 Cats, and More Cats
// Demand for Cat Simulator 1.0 is exploding! For v2 we’re going to go beyond the domes􏰀c cat to model Tigers, Lions,
// and Panthers in addi􏰀on to the Cat. Define a trait Feline and then define all the different species as subtypes of
// Feline. To make things interesti􏰀ng, define:
// • on Feline a colour as before;
// • on Feline a String sound, which for a cat is "meow" and is "roar" for all other felines;
// • only Cat has a favourite food; and
// • Lions have an Int maneSize.

trait Feline {
  def colour: String
  def sound: String = "roar"
}

case class Cat(colour: String, favouriteFood: String) extends Feline {
  override def sound: String = "meow"
}

case class Lion(colour: String, maneSize: Int) extends Feline

case class Tiger(colour: String) extends Feline

case class Panther(colour: String) extends Feline

// 4.1.4.2 Shaping Up With Traits
// Define a trait called Shape and give it three abstract methods:
// • sides returns the number of sides;
// • perimeter returns the total length of the sides;
// • area returns the area.
// Implement Shape with three classes: Circle, Rectangle, and Square. In each case provide implementations of each of
// the three methods. Ensure that the main constructor parameters of each shape (e.g. the radius of the circle) are
// accessible as fields.
// Tip: The value of π is accessible as math.Pi.

sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
}

case class Circle(radius: Double) extends Shape {
  val sides = 1
  def perimeter: Double = 2 * math.Pi * radius
  def area: Double = math.Pi * math.pow(radius, 2)
}

case class Rectangle(height: Double, width: Double) extends Shape {
  val sides = 4
  def perimeter: Double = 2 * height + 2 * width
  def area: Double = height * width
}

case class Square(size: Double) extends Shape {
  val sides = 4
  def perimeter: Double = size * 4
  def area: Double = size * size
}

// 4.1.4.3 Shaping Up 2 (Da Streets)
// The soluti􏰀on from the last exercise delivered three dis􏰀nct types of shape. However, it doesn’t model the
// relati􏰀onships between the three correctly. A Square isn’t just a Shape—it’s also a type of Rectangle where the
// width and height are the same.
// Refactor the soluti􏰀on to the last exercise so that Square and Rectangle are subtypes of a common type Rectangular.
//Tip: A trait can extend another trait.

sealed trait RectangleTrait extends Shape {
  def width: Double
  def height: Double
  val sides = 4
  def perimeter: Double = 2 * height + 2 * width
  def area: Double = height * width
}

case class Rectangle2(height: Double, width: Double) extends RectangleTrait

case class Square2(size: Double) extends RectangleTrait {
  val width = size
  val height = size
}

// 4.2.2 Exercises 4.2.2.1 Printing Shapes
// Let’s revisit the Shapes example from Section [@sec:traits:shaping-up-2].
// First make Shape a sealed trait. Then write a singleton object called Draw with an apply method that takes a Shape
// as an argument and returns a description of it on the console. For example:
//  Draw(Circle(10))
//  // res1: String = A circle of radius 10.0cm
//  Draw(Rectangle(3, 4))
//  // res2: String = A rectangle of width 3.0cm and height 4.0cm
// Finally, verify that the compiler complains when you comment out a case clause.

object Draw {
  def apply(shape: Shape): String = {
    shape match {
      case Circle(radius) => {
        s"A circle of radius ${radius}cm"
      }
      case Rectangle2(height, width) => {
        s"A rectangle of ${width}cm and height ${height}cm"
      }
      case Square2(size) => {
        s"A square of size ${size}cm"
      }
      case Rectangle(height, width) => {
        s"A rectangle of ${width}cm and height ${height}cm"
      }
      case Square(size) => {
        s"A square of size ${size}cm"
      }
    }
  }
}

Draw(Circle(10))
Draw(Rectangle(3, 4))

