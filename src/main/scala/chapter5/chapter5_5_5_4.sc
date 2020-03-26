object exercise_5_5_5_4 {

  sealed trait LinkedList[A] {
    def map[B](fn: A => B): LinkedList[B] =
      this match {
        case Pair(hd, tl) => Pair(fn(hd), tl.map(fn))
        case End() => End[B]()
      }
  }

  case class Pair[A](hd: A, tl: LinkedList[A]) extends LinkedList[A]

  case class End[A]() extends LinkedList[A]


  // 5.5.4.1 Mapping Lists
  // Given the following list
  val list: LinkedList[Int] = Pair(1, Pair(2, Pair(3, End())))
  println(list)
  //• double all the elements in the list;
  println(list.map(_ * 2))
  //• add one to all the elements in the list; and
  println(list.map(_ + 1))
  //• divide by three all the elements in the list.
  println(list.map(_ / 3.0))

  // 5.5.4.2 Mapping Maybe
  // Implement map for Maybe.
  sealed trait Maybe[A] {
    def map[B](fn: A => B): Maybe[B] = {
      this match {
        case Full(value) => Full(fn(value))
        case Empty() => Empty[B]()
      }
    }

    //For bonus points, implement map in terms of flatMap.
    def map2[B](fn: A => B): Maybe[B] = flatMap(value => Full(fn(value)))

    def flatMap[B](fn: A => Maybe[B]): Maybe[B] =
      this match {
        case Full(v) => fn(v)
        case Empty() => Empty[B]()
      }
  }

  final case class Full[A](value: A) extends Maybe[A]

  final case class Empty[A]() extends Maybe[A]

  // 5.5.4.3 Sequencing Computations
  // We’re going to use Scala’s builtin List class for this exercise as it has a
  // flatMap method.
  // Given this list
  val list2 = List(1, 2, 3)
  println(list2)
  // return a List[Int] containing both all the elements and their negation. Order
  // is not important. Hint: Given an element create a list containing it and its
  // negation.
  println(list2.flatMap(x => List(x, -1 * x)))
  // Given this list
  val list3: List[Maybe[Int]] = List(Full(3), Full(2), Full(1))
  println(list3)
  // return a List[Maybe[Int]] containing None for the odd elements. Hint: If
  // x % 2 == 0 then x is even.
  println(list3.map(x => x match {
    case Full(v) if (v % 2 == 0) => Full(v)
    case Full(v) if (v % 2 != 0) => Empty[Int]()
    case Empty() => Empty[Int]()
  }))

  // Better textbook solution
  // list.map(maybe => maybe.flatMap[Int] { x => if (x % 2 == 0) Full(x) else Empty() })

  // 5.5.4.4 Sum
  // Recall our Sum type.
  sealed trait Sum[A, B] {
    def fold[C](failure: A => C, success: B => C): C =
      this match {
        case Failure(a) => failure(a)
        case Success(b) => success(b)
      }

    def map[C](f: B => C): Sum[A, C] = {
      this match {
        case Failure(value) => Failure(value)
        case Success(value) => Success(f(value))
      }
    }

    def flatMap[C](f: B => Sum[A, C]): Sum[A, C] = {
      this match {
        case Failure(value) => Failure(value)
        case Success(value) => f(value)
      }
    }
  }

  final case class Failure[A, B](value: A) extends Sum[A, B]

  final case class Success[A, B](value: B) extends Sum[A, B]

  // To prevent a name collision between the built-in Either, rename the Left
  // and Right cases to Failure and Success respectively.

  // Now things are going to get a bit trickier. We are going to implement map and
  // flatMap, again using pattern matching in the Sum trait. Start with map. The
  // general recipe for map is to start with a type like F[A] and apply a function
  // A => B to get F[B]. Sum however has two generic type parameters. To make
  // it fit the F[A] pattern we’re going to fix one of these parameters and allow
  // map to alter the other one. The natural choice is to fix the type parameter
  // associated with Failure and allow map to alter a Success. This corresponds
  // to “fail-fast” behaviour. If our Sum has failed, any sequenced computations
  // don’t get run.

  // In summary map should have type
  // def map[C](f: B => C): Sum[A, C]

  // Now implement flatMap using the same logic as map.
}

exercise_5_5_5_4
