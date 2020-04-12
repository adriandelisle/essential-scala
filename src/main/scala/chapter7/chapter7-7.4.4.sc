object chapter7_7_4_4 {

  // 7.4.4.1 Equality Again
  // In the previous section we defined a trait Equal along with some implementations for Person.
  case class Person(name: String, email: String)

  trait Equal[A] {
    def equal(v1: A, v2: A): Boolean
  }

  object EmailEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person): Boolean =
      v1.email == v2.email
  }

  object NameEmailEqual extends Equal[Person] {
    def equal(v1: Person, v2: Person): Boolean =
      v1.email == v2.email && v1.name == v2.name
  }

  implicit val nameAndEmail = NameEmailEqual

  object Eq {
    def apply[A](a: A, b: A)(implicit equal: Equal[A]): Boolean =
      equal.equal(a, b)
  }

  // Implement an object called Eq with an apply method. This method should
  // accept two explicit parameters of type A and an implicit Equal[A]. It should
  // perform the equality checking using the provided Equal. With appropriate
  // implicits in scope, the following code should work
  println(Eq(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com")))

  // Package up the different Equal implementations as implicit values in their own
  // objects, and show you can control the implicit selection by changing which
  // object is imported.
  object EmailEqualImplicit {
    implicit object EmailEqual extends Equal[Person] {
      def equal(v1: Person, v2: Person): Boolean =
        v1.email == v2.email
    }
  }

  object NameEmailEqualImplicit {
    object NameEmailEqual extends Equal[Person] {
      def equal(v1: Person, v2: Person): Boolean =
        v1.email == v2.email && v1.name == v2.name
    }
  }

  object uses {
    def emailEqual(): Unit = {
      import EmailEqualImplicit._
      println(Eq(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com")))
    }

    def nameEmailEqual(): Unit = {
      import NameEmailEqualImplicit._
      println(Eq(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com")))
    }
  }

  // Now implement an interface on the companion object for Equal using the
  // no-argument apply method pattern. The following code should work.
  object Equal {
    def apply[A](implicit equal: Equal[A]): Equal[A] =
      equal
  }

  // import NameAndEmailImplicit._
  Equal[Person].equal(Person("Noel", "noel@example.com"), Person("Noel", "noel@example.com"))
  // Which interface style do you prefer?
  // The no-argument apply method pattern seems leaner to use.
}

chapter7_7_4_4
