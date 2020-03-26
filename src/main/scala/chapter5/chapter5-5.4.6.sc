object exercise_5_5_4_6 {

  // 5.4.6.1 Generics versus Traits
  // Sum types and product types are general concepts that allow us to model
  // almost any kind of data structure. We have seen two methods of writing these
  // types—traits and generics. When should we consider using each?

  // type-traits seem geared towards specific data structures ie modeling data
  // generics seem more useful in creating library data structures such as Tuples and Options

  // 5.4.6.2 Folding Maybe
  // In this section we implemented a sum type for modelling optional data:
  // Implement fold for this type.
  sealed trait Maybe[A] {
    def fold[B](full: A => B, empty: B): B = {
      this match {
        case Full(value) => full(value)
        case Empty() => empty
      }
    }
  }

  final case class Full[A](value: A) extends Maybe[A]

  final case class Empty[A]() extends Maybe[A]

  // 5.4.6.3 Folding Sum
  // In this section we implemented a generic sum type:
  // Implement fold for Sum
  sealed trait Sum[A, B] {
    def fold[C](left: (A) => C, right: (B) => C) = {
      this match {
        case Left(value) => left(value)
        case Right(value) => right(value)
      }
    }
  }

  final case class Left[A, B](value: A) extends Sum[A, B]

  final case class Right[A, B](value: B) extends Sum[A, B]

}

exercise_5_5_4_6
