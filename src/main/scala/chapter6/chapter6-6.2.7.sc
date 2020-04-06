object chapter6_6_2_7 {

  // The goals of this exercise are for you to learn your way around the collections API, but more importantly to learn to use types to drive implementation.
  // When approaching each exercise you should answer:
  //  1. What is the type of the data we have available?
  //  2. What is the type of the result we want?
  //  3. What is the type of the operations we will use?
  // When you have answered these questions look at the type table above to find
  // the correct method to use. Done in this way the actual programming should
  // be straightforward.
  // 6.2.7.1 Heroes of the Silver Screen
  // These exercises re-use the example code from the Intranet Movie Database
  // exercise from the previous section:
  case class Film(
                   name: String,
                   yearOfRelease: Int,
                   imdbRating: Double)

  case class Director(
                       firstName: String,
                       lastName: String,
                       yearOfBirth: Int,
                       films: Seq[Film])

  val memento = new Film("Memento", 2000, 8.5)
  val darkKnight = new Film("Dark Knight", 2008, 9.0)
  val inception = new Film("Inception", 2010, 8.8)
  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7)
  val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9)
  val unforgiven = new Film("Unforgiven", 1992, 8.3)
  val granTorino = new Film("Gran Torino", 2008, 8.2)
  val invictus = new Film("Invictus", 2009, 7.4)
  val predator = new Film("Predator", 1987, 7.9)
  val dieHard = new Film("Die Hard", 1988, 8.3)
  val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6)
  val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8)

  val eastwood = new Director("Clint", "Eastwood", 1930,
    Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))
  val mcTiernan = new Director("John", "McTiernan", 1951,
    Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))
  val nolan = new Director("Christopher", "Nolan", 1970,
    Seq(memento, darkKnight, inception))
  val someGuy = new Director("Just", "Some Guy", 1990, Seq())
  val directors = Seq(eastwood, mcTiernan, nolan, someGuy)

  // Nolan Films
  // Staring with the definition of nolan, create a list containing the names of the
  // films directed by Christopher Nolan.
  println(nolan.films.map(film => film.name))

  // Cinephile
  // Starting with the definition of directors, create a list containing the names
  // of all films by all directors.
  println(directors.flatMap(director => director.films.map(_.name)))

  // Vintage McTiernan
  // Starting with mcTiernan, find the date of the earliest McTiernan film.
  // Tip: you can concisely find the minimum of two numbers a and b using
  // math.min(a, b).
  println(mcTiernan.films.foldLeft(Int.MaxValue)((current, film) => scala.math.min(current, film.yearOfRelease)))

  // High Score Table
  // Starting with directors, find all films sorted by descending IMDB rating:
  println(directors.flatMap(_.films.sortWith((a, b) => a.imdbRating > b.imdbRating)))

  // Starting with directors again, find the average score across all films:
  val filmRatings = directors.flatMap(_.films.map(_.imdbRating))
  println(filmRatings.foldLeft(0.0) { (a, b) =>
    a + b /**/
  } / filmRatings.length)

  // Tonight’s Listings
  // Starting with directors, print the following for every film: "Tonight only! FILM NAME by DIRECTOR!"
  directors.foreach(director =>
    director.films.foreach(film =>
      println(s"Tonight only! ${film.name} by ${director.firstName} ${director.lastName}!")
    )
  )

  // From the Archives
  // Finally, starting with directors again, find the earliest film by any director:
  val filmAges = directors.flatMap(_.films.map(_.yearOfRelease))
  println(filmAges.tail.foldLeft(filmAges.head) { (current, next) =>
    scala.math.min(current, next)
  })

  // 6.2.7.2 Do-It-Yourself
  // Now we know the essential methods of Seq, we can write our own versions
  // of some other library methods.

  // Minimum
  // Write a method to find the smallest element of a Seq[Int].
  def minimum(seq: Seq[Int]): Int =
    seq.foldLeft(Int.MaxValue)((acc, current) => scala.math.min(acc, current))
  println(minimum(Seq(1, 3, 5, -1)))

  // Unique
  // Given Seq(1, 1, 2, 4, 3, 4) create the sequence containing each number only once. Order is not important,
  // so Seq(1, 2, 4, 3) or Seq(4, 3, 2, 1) are equally valid answers.
  // Hint: Use contains to check if a sequence contains a value.
  def unique[A](seq: Seq[A]): Seq[A] =
    seq.foldLeft(Seq[A]())( (acc, element) =>
      if (acc.contains(element)) acc else acc :+ element
    )
  println(unique(Seq(1, 1, 2, 4, 3, 4)))

  // Reverse
  // Write a function that reverses the elements of a sequence. Your output does
  // not have to use the same concrete implementation as the input.
  // Hint: use foldLeft.
  def reverse[A](seq: Seq[A]): Seq[A] =
    seq.foldLeft(Seq[A]())((acc, element) => element +: acc)
  println(reverse(Seq(1, 2, 4, 3)))

  // Map
  // Write map in terms of foldRight.
  def map[A, B](seq: Seq[A], f: A => B): Seq[B] =
    seq.foldRight(Seq[B]())((element, acc) => f(element) +: acc)
  println(map(Seq(1, 2, 4, 3), (x: Int) => x * 2))

  // Fold Left
  // Write your own implementation of foldLeft that uses foreach and mutable
  // state. Remember you can create a mutable variable using the var keyword,
  // and assign a new value using =. For example
  // var mutable = 1
  // // mutable: Int = 1
  // mutable = 2
  // // mutable: Int = 2
  def foldLeft[A, B](seq: Seq[A], initial: B, f: (B, A) => B): B = {
    var result = initial
    seq.foreach(element => result = f(result, element))
    result
  }
  println(foldLeft(Seq(1, 2, 4, 3), 0, (acc: Int, element: Int) => acc + element))

  // There are many other methods on sequences. Consult the API documentation
  //  the Seq trait for more information.
}

chapter6_6_2_7
