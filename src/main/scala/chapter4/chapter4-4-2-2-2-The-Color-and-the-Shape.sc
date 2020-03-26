object exercise_4_4_2_2_2 {

  // 4.2.2.2 The Color and the Shape
  // Write a sealed trait Color to make our shapes more interesting.
  // • give Color three proper􏰀es for its RGB values;
  // • create three predefined colours: Red, Yellow, and Pink;
  // • provide a means for people to produce their own custom Colors with
  //   their own RGB values;
  // • provide a means for people to tell whether any Color is “light” or “dark”.
  // A lot of this exercise is deliberately open to interpretation. The important thing is to practice working with traits,
  // classes, and objects.
  // Decisions such as how to model colours and what is considered a light or dark colour can either be le􏰁 up to you or
  // discussed with other class members.
  // Edit the code for Shape and its subtypes to add a colour to each shape.
  // Finally, update the code for Draw.apply to print the colour of the argument
  // as well as its shape and dimensions:
  // • if the argument is a predefined colour, print that colour by name:
  // • if the argument is a custom colour rather than a predefined one, print the word “light” or “dark” instead.
  // You may want to deal with the colour in a helper method.

  sealed trait Color {
    def R: Double

    def G: Double

    def B: Double

    def isLight: Boolean = if ((R + B + G) / 3.0 > 0.5) true else false
  }

  case object Red extends Color {
    val R = 1.0
    val G = 0.0
    val B = 0.0
  }

  case object Yellow extends Color {
    val R = 1.0
    val G = 0.0
    val B = 0.0
  }

  case object Pink extends Color {
    val R = 1.0
    val G = 0.0
    val B = 1.0
  }

  final case class CustomColor(R: Double, G: Double, B: Double) extends Color

  sealed trait Shape {
    def sides: Int

    def perimeter: Double

    def area: Double

    def color: Color
  }

  case class Circle(radius: Double, color: Color) extends Shape {
    val sides = 1

    def perimeter: Double = 2 * math.Pi * radius

    def area: Double = math.Pi * math.pow(radius, 2)
  }

  sealed trait RectangleTrait extends Shape {
    def width: Double

    def height: Double

    val sides = 4

    def perimeter: Double = 2 * height + 2 * width

    def area: Double = height * width
  }

  case class Rectangle(height: Double, width: Double, color: Color) extends RectangleTrait

  case class Square(size: Double, color: Color) extends RectangleTrait {
    val width = size
    val height = size
  }

  object Draw {
    def apply(shape: Shape): String = {
      shape match {
        case Circle(radius, color) =>
          s"A ${Draw(color)} circle of radius ${radius}cm"
        case Rectangle(height, width, color) =>
          s"A ${Draw(color)} rectangle of ${width}cm and height ${height}cm"
        case Square(size, color) =>
          s"A ${Draw(color)} square of size ${size}cm"
      }
    }

    def apply(color: Color): String = {
      color match {
        case Yellow => "yellow"
        case Red => "red"
        case Pink => "pink"
        case color => if (color.isLight) "light" else "dark"
      }
    }
  }

  println(Draw(Circle(10, Yellow)))
  println(Draw(Rectangle(3, 4, Pink)))
  println(Draw(Circle(10, CustomColor(1, 1, 1))))
  println(Draw(Circle(10, CustomColor(0, 0, 0))))

}

exercise_4_4_2_2_2
