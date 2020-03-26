// 5.1.3 Exercises
object exercise_5_1_3 {

  // 5.1.3.1 Generic List
  // Our IntList type was defined as
  // sealed trait IntList
  // case object End extends IntList
  // final case class Pair(head: Int, tail: IntList) extends IntList
  // Change the name to LinkedList and make it generic in the type of data
  // stored in the list.

  sealed trait LinkedList[A] {
    def length: Int = {
      this match {
        case End() => 0
        case Pair(_, tail) => 1 + tail.length
      }
    }

    def contains(value: A): Boolean = {
      this match {
        case End() => false
        case Pair(head, tail) => if (head == value) true else tail.contains(value)
      }
    }

    def apply(index: Int): A = {
      this match {
        case End() => throw new Exception("Index does not exist")
        case Pair(head, tail) => if (index == 0) head else tail(index - 1)
      }
    }

    def apply2(index: Int): Result[A] = {
      this match {
        case End() => Failure[A]("Index out of bounds")
        case Pair(head, tail) => if (index == 0) Success[A](head) else tail.apply2(index - 1)
      }
    }
  }

  case class End[A]() extends LinkedList[A]

  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]

  // 5.1.3.2 Working With Generic Types
  // There isn’t much we can do with our LinkedList type. Remember that types
  // define the available operations, and with a generic type like A there isn’t a
  // concrete type to define any available operations. (Generic types are made
  // concrete when a class is instantiated, which is too late to make use of the
  // information in the definition of the class.)
  // However, we can still do some useful things with our LinkedList! Implement
  // length, returning the length of the LinkedList. Some test cases are below.

  val example = Pair(1, Pair(2, Pair(3, End())))
  assert(example.length == 3)
  assert(example.tail.length == 2)
  assert(End().length == 0)

  // On the JVM we can compare all values for equality. Implement a method
  // contains that determines whether or not a given item is in the list. Ensure
  // your code works with the following test cases:
  val example2 = Pair(1, Pair(2, Pair(3, End())))
  assert(example2.contains(3) == true)
  assert(example2.contains(4) == false)
  assert(End().contains(0) == false)
  // This should not compile
  //example.contains("not an Int")

  // Implement a method apply that returns the nth item in the list
  // Hint: If you need to signal an error in your code (there’s one situation in which
  // you will need to do this), consider throwing an exception. Here is an example:
  // throw new Exception("Bad things happened")
  // Ensure your solution works with the following test cases:
  val example3 = Pair(1, Pair(2, Pair(3, End())))
  assert(example3(0) == 1)
  assert(example3(1) == 2)
  assert(example3(2) == 3)
  assert(try {
    example3(3)
    false
  } catch {
    case e: Exception => true
  })

  // Throwing an exception isn’t cool. Whenever we throw an exception we lose
  // type safety as there is nothing in the type system that will remind us to deal
  // with the error. It would be much beer to return some kind of result that
  // encodes we can succeed or failure. We introduced such a type in this very
  // section.
  sealed trait Result[A]
  case class Success[A](result: A) extends Result[A]
  case class Failure[A](reason: String) extends Result[A]
  // Change apply so it returns a Result, with a failure case indicating what went
  // wrong. Here are some test cases to help you:
  assert(example.apply2(0) == Success(1))
  assert(example.apply2(1) == Success(2))
  assert(example.apply2(2) == Success(3))
  assert(example.apply2(3) == Failure("Index out of bounds"))

}

exercise_5_1_3
