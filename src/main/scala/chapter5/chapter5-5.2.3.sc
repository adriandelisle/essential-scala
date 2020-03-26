// 5.2.3 Exercises

object exercise_5_2_3 {

  // 5.2.3.1 A Better Abstraction
  // We started developing an abstraction over sum, length, and product which
  // we sketched out as
  // def abstraction(end: Int, f: ???): Int =
  // this match {
  // case End => end
  // case Pair(hd, tl) => f(hd, tl.abstraction(end, f))
  //  }
  // Rename this function to fold, which is the name it is usually known as, and
  // finish the implementation.
  sealed trait IntList {
    def fold(end: Int, f: (Int, Int) => Int): Int =
      this match {
        case End => end
        case Pair(hd, tl) => f(hd, tl.fold(end, f))
      }

    def fold2[A](end: A, f: (Int, A) => A): A =
      this match {
        case End => end
        case Pair(hd, tl) => f(hd, tl.fold2(end, f))
      }

    def double: IntList = fold2(End, (head, tail: IntList) => Pair(head * 2, tail))

    def sum: Int = fold(0, (x, y) => x + y)
    def product : Int = fold(1, (x, y) => x * y)
    def length: Int = fold(0, (_, tail) => tail + 1)
  }

  case object End extends IntList

  final case class Pair(head: Int, tail: IntList) extends IntList

  val example = Pair(1, Pair(2, Pair(3, End)))
  assert(example.sum == 6)
  assert(End.sum == 0)
  assert(example.product == 6)
  assert(example.length == 3)
  assert(example.double == Pair(2, Pair(4, Pair(6, End))))

  // Is it more convenient to rewrite methods in terms of fold if they were implemented using pattern matching or
  // polymorphic? What does this tell us about the best use of fold?
  // Its easier to write with pattern matching.

  // Why can’t we write our double method in terms of fold? Is it feasible we
  // could if we made some change to fold?
  // Folds as its written is limited to just Ints, not IntLists. If we used generics we could make it work.

  // Implement a generalised version of fold and rewrite double in terms of it.
}

exercise_5_2_3
