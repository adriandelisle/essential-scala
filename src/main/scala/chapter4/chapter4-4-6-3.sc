object exercise_4_4_6_3 {

  // Using our definition of IntList
  sealed trait IntList {
    def length: Int =
      this match {
        case End => 0
        case Pair(_, tail) => 1 + tail.length
      }

    def product: Int =
      this match {
        case End => 1
        case Pair(head, tail) => head * tail.product
      }

    def double: IntList =
      this match {
        case End => End
        case Pair(head, tail) => Pair(head * 2, tail.double)
      }
  }

  case object End extends IntList

  final case class Pair(head: Int, tail: IntList) extends IntList

  // define a method length that returns the length of the list. There is test data
  // below you can use to check your solution. For this exercise it is best to use
  // pattern matching in the base trait.
  val example = Pair(1, Pair(2, Pair(3, End)))
  assert(example.length == 3)
  assert(example.tail.length == 2)
  assert(End.length == 0)

  // Define a method to compute the product of the elements in an IntList. Test cases are below.
  assert(example.product == 6)
  assert(example.tail.product == 6)
  assert(End.product == 1)

  // Define a method to double the value of each element in an IntList, returning
  // a new IntList. The following test cases should hold:
  assert(example.double == Pair(2, Pair(4, Pair(6, End))))
  assert(example.tail.double == Pair(4, Pair(6, End)))
  assert(End.double == End)

  // 4.6.3.2 The Forest of Trees
  // A binary tree of integers can be defined as follows:
  // A Tree is a Node with a left and right Tree or a Leaf with an element of type Int.
  // Implement this algebraic data type.
  sealed trait IntTree {
    def sum: Int =
      this match {
        case Leaf(value) => value
        case Node(left, right) => left.sum + right.sum
      }
    def double: IntTree =
      this match {
        case Leaf(value) => Leaf(value * 2)
        case Node(left, right) => Node(left.double, right.double)
      }
  }
  final case class Leaf(value: Int) extends IntTree
  final case class Node(left: IntTree, right: IntTree) extends IntTree

  //Implement sum and double on Tree using polymorphism and pattern matching.
  val treeExample = Node(Node(Leaf(1), Leaf(2)), Leaf(3))
  assert(treeExample.sum == 6)
  assert(treeExample.double == Node(Node(Leaf(2), Leaf(4)), Leaf(6)))
}

exercise_4_4_6_3
