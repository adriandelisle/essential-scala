// 5.3.4 Exercises

object exercise_5_3_4 {

  // 5.3.4.1 Tree
  // A binary tree can be defined as follows:
  // A Tree of type A is a Node with a left and right Tree or a Leaf with an element
  // of type A.
  // Implement this algebraic data type along with a fold method.

  sealed trait Tree[A] {
    def fold[B](node: (B, B) => B, leaf: A => B): B
  }

  case class Leaf[A](value: A) extends Tree[A] {
    def fold[B](node: (B, B) => B, leaf: A => B): B = {
      leaf(value)
    }
  }

  case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A] {
    def fold[B](node: (B, B) => B, leaf: A => B): B = {
      node(left.fold(node, leaf), right.fold(node, leaf))
    }
  }

  // Using fold convert the following Tree to a String
  val tree: Tree[String] =
    Node(Node(Leaf("To"), Leaf("iterate")),
      Node(Node(Leaf("is"), Leaf("human,")),
        Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))

  // Remember you can append Strings using the + method.

  val treeString = tree.fold[String]((left, right) => s"$left $right", a => a)
  println(treeString)
}

exercise_5_3_4
