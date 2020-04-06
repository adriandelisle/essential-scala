object chapter6_6_5_1 {
  // 6.5.1.1 Adding Things
  // Write a method addOptions that accepts two parameters of type
  // Option[Int] and adds them together. Use a for comprehension to
  // structure your code.
  println("6.5.1.1 Adding Things")

  def addOptions(x: Option[Int], y: Option[Int]): Option[Int] =
    for {
      a <- x
      b <- y
    } yield a + b

  println(addOptions(None, Some(1)))
  println(addOptions(Some(1), Some(2)))

  // Write a second version of your code using map and flatMap instead of a for
  // comprehension.
  def add(x: Option[Int], y: Option[Int]): Option[Int] =
    x.flatMap(a => y.map(b => a + b))

  println(add(None, Some(1)))
  println(add(Some(1), Some(2)))

  // 6.5.1.2 Adding All of the Things
  // Overload addOptions with another implementation that accepts three
  // Option[Int] parameters and adds them all together.
  println("6.5.1.2 Adding All of the Things")

  def addOptions(x: Option[Int], y: Option[Int], z: Option[Int]): Option[Int] =
    for {
      a <- x
      b <- y
      c <- z
    } yield a + b + c

  println(addOptions(None, Some(1), None))
  println(addOptions(Some(1), Some(2), Some(3)))

  // Write a second version of your code using map and flatMap instead of a for
  // comprehension.
  def add(x: Option[Int], y: Option[Int], z: Option[Int]): Option[Int] =
    x.flatMap(a => y.flatMap(b => z.map(c => a + b + c)))

  println(add(None, Some(1), None))
  println(add(Some(1), Some(2), Some(3)))

  // 6.5.1.3 A(nother) Short Division Exercise
  // Write a method divide that accepts two Int parameters and divides one by
  // the other. Use Option to avoid exceptions when the denominator is 0.
  println("6.5.1.3 A(nother) Short Division Exercise")

  def divide(num: Int, dom: Int): Option[Int] =
    if (dom == 0) None else Some(num / dom)

  // Using your divide method and a for comprehension, write a method called
  // divideOptions that accepts two parameters of type Option[Int] and divides one by the other:
  def divideOptions(num: Option[Int], dom: Option[Int]): Option[Int] =
    for {
      a <- num
      b <- dom
      c <- divide(a, b)
    } yield c

  println(divideOptions(Some(1), Some(2)))
  println(divideOptions(Some(1), Some(0)))

  // 6.5.1.4 A Simple Calculator
  // A final, longer exercise. Write a method called calculator that accepts three
  // string parameters:
  println("6.5.1.4 A Simple Calculator")

  def readInt(str: String): Option[Int] =
    if (str matches "-?\\d+") Some(str.toInt) else None

  def calculator(operand1: String, operator: String, operand2: String): Unit = {
    def operation(opt1: Int, opt2: Int): Option[Int] =
      operator match {
        case "+" => Some(opt1 + opt2)
        case "-" => Some(opt1 - opt2)
        case "/" => divide(opt1, opt2)
        case "*" => Some(opt1 * opt2)
        case _ => None
      }

    val result = for {
      op1 <- readInt(operand1)
      op2 <- readInt(operand2)
      result <- operation(op1, op2)
    } yield result

    result match {
      case None => println(s"Calculation failed: ${operand1} ${operator} ${operand2}")
      case Some(value) => println(s"${operand1} ${operator} ${operand2} = ${value}")
    }
  }

  calculator("1", "+", "2")
  calculator("a", "+", "2")
  calculator("1", "-", "2")
  calculator("1", "*", "2")
  calculator("4", "/", "2")
  calculator("1", "/", "0")
  // and behaves as follows:
  //  1. Convert the operands to Ints;
  //  2. Perform the desired mathematical operator on the two operands:
  //    • provide support for at least four operations: +, -, * and /;
  //    • use Option to guard against errors (invalid inputs or division by zero).
  //  3. Finally print the result or a generic error message.
  // Tip: Start by supporting just one operator before extending your method to
  // other cases.

  // For the enthusiastic only, write a second version of your code using flatMap
  // and map
  println("calculator2")

  def calculator2(operand1: String, operator: String, operand2: String): Unit = {
    def operation(opt1: Int, opt2: Int): Option[Int] =
      operator match {
        case "+" => Some(opt1 + opt2)
        case "-" => Some(opt1 - opt2)
        case "/" => divide(opt1, opt2)
        case "*" => Some(opt1 * opt2)
        case _ => None
      }

    val result = readInt(operand1).flatMap(a =>
      readInt(operand2).flatMap((b =>
        operation(a, b).map(c => c)
        )))

    result match {
      case None => println(s"Calculation failed: ${operand1} ${operator} ${operand2}")
      case Some(value) => println(s"${operand1} ${operator} ${operand2} = ${value}")
    }
  }

  calculator2("1", "+", "2")
  calculator2("a", "+", "2")
  calculator2("1", "-", "2")
  calculator2("1", "*", "2")
  calculator2("4", "/", "2")
  calculator2("1", "/", "0")
}

chapter6_6_5_1
