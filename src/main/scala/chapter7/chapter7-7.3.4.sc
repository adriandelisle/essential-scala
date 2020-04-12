object chapter7_7_3_4 {

  // 7.3.4.1 Equality
  // Scala provides two equality predicates: by value (==) and by reference (eq).
  // Nonetheless, we sometimes need additional predicates. For instance, we
  // could compare people by just email address if we were validating new user
  // accounts in some web application.
  // Implement a trait Equal of some type A, with a method equal that compares
  // two values of type A and returns a Boolean. Equal is a type class.
  trait Equal[A] {
    def equal(a: A, b: A): Boolean
  }

  // Our Person class is
  case class Person(name: String, email: String)

  // Implement instances of Equal that compare for equality by email address only,
  // and by name and email.
  object PersonByEmail extends Equal[Person] {
    def equal(a: Person, b: Person): Boolean =
      a.email == b.email
  }

  object PersonNameAndEmail extends Equal[Person] {
    def equal(a: Person, b: Person): Boolean =
      a.email == b.email && a.name == b.name
  }

}

chapter7_7_3_4
