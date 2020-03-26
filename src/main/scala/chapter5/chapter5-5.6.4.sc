object exercise_5_5_6_4 {

  // 5.6.4.1 Exercise: Covariant Sum
  // Implement a covariant Sum using the covariant generic sum type pattern.

  sealed trait Sum[+A, +B] {
    def flatMap[AA >: A, C](f: B => Sum[AA, C]): Sum[AA, C] =
      this match {
        case Failure(v) => Failure(v)
        case Success(v) => f(v)
      }
  }

  final case class Success[B](value: B) extends Sum[Nothing, B]

  final case class Failure[A](value: A) extends Sum[A, Nothing]

  // 5.6.4.2 Exercise: Some sort of flatMap
  //Implement flatMap and verify you receive an error like
  // error: covariant type A occurs in contravariant position in type B =>
  // Sum[A,C] of value f
  // def flatMap[C](f: B => Sum[A, C]): Sum[A, C] =
}

exercise_5_5_6_4
