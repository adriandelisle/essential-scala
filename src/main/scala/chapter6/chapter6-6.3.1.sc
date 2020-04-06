object chapter6_6_3_1 {

  // (More) Heroes of the Silver Screen
  // Repeat the following exercises from the previous section without using map or flatMap:
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
  // List the names of the films directed by Christopher Nolan.
  val nolanFilms = for {
    film <- nolan.films
  } yield film.name
  println(nolanFilms)

  // Cinephile
  // List the names of all films by all directors.
  val allFilms = for {
    director <- directors
    film <- director.films
  } yield film.name
  println(allFilms)

  // High Score Table
  // Find all films sorted by descending IMDB rating:
  val allFilmsSortedByRating = (for {
    director <- directors
    film <- director.films
  } yield film).sortWith((a, b) => a.imdbRating > b.imdbRating)
  println(allFilmsSortedByRating)

  //Tonight’s Listings
  //Print the following for every film: "Tonight only! FILM NAME by DIRECTOR!"
  for {
    director <- directors
    film <- director.films
  } println(s"Tonight only! ${film.name} by ${director.firstName} ${director.lastName}!")

}

chapter6_6_3_1
