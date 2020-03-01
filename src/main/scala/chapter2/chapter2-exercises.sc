// 2.1.4 Exercises

//Using the Scala console or worksheet, determine the type and value of the
// following expressions:

1 + 2 // 3: int

"3".toInt // 3: int

//"foo".toInt // NumberFormatException

// 2.2.5 Exercises

//Rewrite in operator-style
//"foo".take(1)
// res14: String = f
"foo" take 1

//Rewrite in method call style
//1+2+3
//// res16: Int = 6
1.+(2).+(3)

// 2.2.5.2 Substitution

//What is the difference between the following expressions? What are the similaries?
//1+2+3 // an expression use operation syntax
//6 // an expression literal

// 2.3.8 Exercises

// What are the values and types of the following Scala literals?
// 42 Int
42
// true Boolean
true
// 123L Long
123L
//42.0 Double
42.0

// What is the difference between the following literals? What is the type and
//value of each?
// 'a' the Char a
// "a" a String with a in it
'a'
"A"

// What is the difference between the following expressions? What is the type
//and value of each?
//"Hello world!" String
//println("Hello world!") function that returns void
"Hello world!"
println("Hello world!")

//What is the type and value of the following literal? Try writing it on the REPL
//  or in a Scala worksheet and see what happens!
// 'Hello world!' doesn't compile

// 2.4.5 Exercises

//2.4.5.1 Cat-o-matique
//The table below shows the names, colour, and favourite foods of three cats.
//Define an object for each cat. (For experienced programmers: we haven’t
//covered classes yet.)

object Oswald {
  val colour = "Black"
  val food = "Milk"
}

object Henderson {
  val colour = "Ginger"
  val food = "Chips"
}

object Quentin {
  val colour = "Tabby and white"
  val food = "Curry"
}

//2.4.5.2 Square Dance!
//  Define an object called calc with a method square that accepts a Double
//  as an argument and… you guessed it… squares its input. Add a method called
// cube that cubes its input calling square as part of its result calculation.

object calc {
  def square(x: Double): Double = {
    x * x
  }

  def cube(x: Double): Double = {
    square(x) * x
  }
}

calc.square(2)
calc.cube(3)

// 2.4.5.3 Precise Square Dance!
//Copy and paste calc from the previous exercise to create a calc2 that is
//generalized to work with Ints as well as Doubles. If you have Java experience,
//this should be fairly straight forward. If not, read the solution below.

object calc2 {
  def square(x: Double): Double = {
    x * x
  }

  def cube(x: Double): Double = {
    square(x) * x
  }

  def square(x: Int): Int = {
    x * x
  }

  def cube(x: Int): Int = {
    square(x) * x
  }
}

calc2.square(2)
calc2.cube(3)

//2.4.5.4 Order of evaluation
//When entered on the console, what does the following program output, and
//what is the type and value of the final expression? Think carefully about the
//types, dependencies, and evaluation behaviour of each field and method.

// b
// a
// c
// a
// a

object argh {
  def a = {
    println("a")
    1
  }

  val b = {
    println("b")
    a + 2
  }

  def c = {
    println("c")
    a
    b + "c"
  }
}
// 3c // 3 // 1
// 3c31
argh.c + argh.b + argh.a

// 2.4.5.5 Greetings, human
//Define an object called person that contains fields called firstName and
//lastName. Define a second object called alien containing a method called
//greet that takes your person as a parameter and returns a greeting using their
//firstName.
//What is the type of the greet method? Can we use this method to greet other
//objects?

object person {
  val firstName = "Bob"
  val lastName = "Smith"
  val name = firstName + " " + lastName
}

object alien {
  def greet(human: person.type ): String = {
    "Greetings " + human.name
  }
}

alien.greet(person)

// 2.4.5.6 The Value of Methods
// Are methods values? Are they expressions? Why might this be the case?
// Expressions? They're evaluated every time they're called

// expressions when called, and values when referenced

// 2.6.4 Exercises

//2.6.4.1 A Classic Rivalry
//  What is the type and value of the following conditional?
// if(1 > 2) "alien" else "predator" // String: "predator"
if(1 > 2) "alien" else "predator"

// 2.6.4.2 A Less Well Known Rivalry
//What about this conditional?
// if(1 > 2) "alien" else 2001 // Int: 2001
if(1 > 2) "alien" else 2001 // Any

// 2.6.4.3 An if Without an else
// What about this conditional?
// if(false) "hello" // Unit
if(false) "hello"
