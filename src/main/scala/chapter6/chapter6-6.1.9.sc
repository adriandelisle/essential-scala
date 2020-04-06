object chapter6_6_1_9 {
  // 6.1.9.1 Documentation
  // Discovering Scala’s collection classes is all about knowing how to read the
  // API documentation. Look up the Seq and List types now and answer the
  // following:
  //  • There is a synonym of length defined on Seq—what is it called?
  //     size
  //  • There are two methods for retrieving the first item in a List – what are they called and how do they differ?
  //     head - get the first element, throws if it doesn't exist
  //     headOption - gets an option that might contain the first element of the collection
  //  • What method can be used to display the elements of the sequence as a string?
  //     mkString
  //  • What method of Option can be used to determine whether the option contains a value?
  //     isDefined

  // 6.1.9.2 Animals
  // Create a Seq containing the Strings "cat", "dog", and "penguin". Bind it
  // to the name animals.
  val animals = Seq("cat", "dog", "penguin")

  // Append the element "tyrannosaurus" to animals and prepend the element
  // "mouse".
  println("mouse" +: animals :+ "tyrannosaurus")

  // What happens if you prepend the Int 2 to animals? Why? Try it out… were
  // you correct?
  // It should throw an error
  println(2 +: animals)

  // I was wrong it creates a List of List(2, cat, dog, penguin)

  // 6.1.9.3 Intranet Movie Database
  // Let’s revisit our films and directors example from the Classes chapter.
  // The code below is a partial rewrite of the previous sample code in which Films
  // is stored as a field of Director instead of the other way around. Copy and
  // paste this into a new Scala worksheet and continue with the exercises below:
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

  // Using this sample code, write implementations of the following methods:
  //  • Accept a parameter numberOfFilms of type Int—find all directors who have directed more than numberOfFilms:
  def directedMoreFilmsThan(numberOfFilms: Int): Seq[Director] =
    directors.filter(director => director.films.length > numberOfFilms)
  println(directedMoreFilmsThan(3).length)

  // • Accept a parameter year of type Int—find a director who was born
  // before that year:
  def bornBefore(year: Int): Option[Director] = directors.find(director => director.yearOfBirth < year)
  println(bornBefore(1940))

  // • Accept two parameters, year and numberOfFilms, and return a list
  // of directors who were born before year who have also directed more
  // than than numberOfFilms:
  def beforeAndMoreFilmsThan(year: Int, numberOfFilms: Int): Seq[Director] =
    directors.filter(director => director.yearOfBirth < year && director.films.length > numberOfFilms)
  println(beforeAndMoreFilmsThan(1960, 4))

  // • Accept a parameter ascending of type Boolean that defaults to true.
  // Sort the directors by age in the specified order:
  def sortByAge(ascending: Boolean = true): Seq[Director] =
    directors.sortWith((a, b) => if (ascending) a.yearOfBirth > b.yearOfBirth else a.yearOfBirth < b.yearOfBirth)
  println(sortByAge())
  println(sortByAge(false))
}

chapter6_6_1_9
