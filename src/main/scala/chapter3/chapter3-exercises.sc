object chapter3_exercises {

  // 3.1.6 Exercises

  // 3.1.6.1 Cats, Again
  // Define a class Cat and then create an object for each cat in the table above.

  class Cat(val name: String, val colour: String, val food: String)

  val oswald = new Cat(name = "Oswald", colour = "Black", food = "Milk")
  val henderson = new Cat(name = "Henderson", colour = "Ginger", food = "Chips")
  val quentin = new Cat(name = "Quentin", colour = "Tabby and white", food = "Curry")

  // 3.1.6.2 Cats on the Prowl
  // Define an object ChipShop with a method willServe.
  // This method should accept a Cat and return true if the cat’s favourite food is
  // chips, and false otherwise.

  object  ChipShip {
    def willServe(cat: Cat): Boolean = cat.food == "Chips"
  }

  println(ChipShip.willServe(oswald))
  println(ChipShip.willServe(henderson))

  /*
  3.1.6.3 Directorial Debut
  Write two classes, Director and Film, with fields and methods as follows:
  • Director should contain:
  – a field firstName of type String
  – a field lastName of type String
  – a field yearOfBirth of type Int
  – a method called name that accepts no parameters and returns the full name
  • Film should contain:
  – a field name of type String
  – a field yearOfRelease of type Int
  – a field imdbRating of type Double
  – a field director of type Director
  - a method directorsAge that returns the age of the director at the 􏰀time of release
  – a method isDirectedBy that accepts a Director as a parameter and returns a Boolean
   */

  class Director(val firstName: String,
                 val lastName: String,
                 val yearOfBirth: Int) {
    def name: String = firstName + " " + lastName
  }

  object Director {
    def apply(firstName: String, lastName: String, yearOfBirth: Int): Director =
      new Director(firstName, lastName, yearOfBirth)
    def older(director1: Director, director2: Director): Director =
      if (director1.yearOfBirth < director2.yearOfBirth) director1 else director2
  }


  class Film(
              val name: String,
              val yearOfRelease: Int,
              val imdbRating: Double,
              val director: Director) {
    def directorsAge: Int = yearOfRelease - director.yearOfBirth
    def isDirectedBy(directedBy: Director): Boolean = director.name == directedBy.name
    def copy(name: String = this.name,
             yearOfRelease: Int = this.yearOfRelease,
             imdbRating: Double = this.imdbRating,
             director: Director = this.director): Film = {
      new Film(name, yearOfRelease, imdbRating, director)
    }
  }

  object Film {
    def apply(name: String, yearOfRelease: Int, imdbRating: Double, director: Director): Film =
      new Film(name, yearOfRelease, imdbRating, director)
    def newer(film1: Film, film2: Film): Film =
      if (film1.yearOfRelease < film2.yearOfRelease) film1 else film2
    def highestRating(film1: Film, film2: Film): Film =
      if (film1.imdbRating > film2.imdbRating) film1 else film2
    def oldestDirectorAtTheTime(film1: Film, film2: Film): Director =
      if (film1.directorsAge > film2.directorsAge) film1.director else film2.director
  }

  val eastwood = new Director("Clint", "Eastwood", 1930)
  val mcTiernan = new Director("John", "McTiernan", 1951)
  val nolan = new Director("Christopher", "Nolan", 1970)
  val someBody = new Director("Just", "Some Body", 1990)
  val memento = new Film("Memento", 2000, 8.5, nolan)
  val darkKnight = new Film("Dark Knight", 2008, 9.0, nolan)
  val inception = new Film("Inception", 2010, 8.8, nolan)
  val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7,
  eastwood)
  val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9,
  eastwood)
  val unforgiven = new Film("Unforgiven", 1992, 8.3, eastwood)
  val granTorino = new Film("Gran Torino", 2008, 8.2, eastwood)
  val invictus = new Film("Invictus", 2009, 7.4, eastwood)
  val predator = new Film("Predator", 1987, 7.9, mcTiernan)
  val dieHard = new Film("Die Hard", 1988, 8.3, mcTiernan)
  val huntForRedOctober  = new Film("The Hunt for Red October", 1990,
  7.6, mcTiernan)
  val thomasCrownAffair  = new Film("The Thomas Crown Affair", 1999, 6.8,
  mcTiernan)

  eastwood.yearOfBirth

  dieHard.director.name

  invictus.isDirectedBy(nolan)

  // Implement a method of Film called copy. This method should accept
  // the same parameters as the constructor and create a new copy of the film.
  // Give each parameter a default value so you can copy a film changing any
  // subset of its values:

  val filmCopy1 = highPlainsDrifter.copy(name = "L'homme des hautes plaines")
  filmCopy1.name
  filmCopy1.imdbRating
  filmCopy1.yearOfRelease
  filmCopy1.director
  val filmCopy2 = thomasCrownAffair.copy(yearOfRelease = 1968, director = new Director("Norman", "Jewison", 1926))
  filmCopy2.name
  filmCopy2.imdbRating
  filmCopy2.yearOfRelease
  filmCopy2.director
  val filmCopy3 = inception.copy().copy().copy()
  filmCopy3.name
  filmCopy3.imdbRating
  filmCopy3.yearOfRelease
  filmCopy3.director

  // 3.1.6.4 A Simple Counter
  // Implement a Counter class. The constructor should take an Int.
  // The methods inc and dec should increment and decrement the counter
  // respectively returning a new Counter. Here’s an example of the usage:
  // new Counter(10).inc.dec.inc.inc.count
  // res23: Int = 12

  class Counter(val count: Int) {
    def inc: Counter = new Counter(this.count + 1)
    def dec: Counter = new Counter(this.count - 1)
  }

  new Counter(10).inc.dec.inc.inc.count

  // 3.1.6.5 Counting Faster
  // Augment the Counter from the previous exercise to allow the user can optionally
  // pass an Int parameter to inc and dec. If the parameter is omi􏰂ed it should
  // default to 1.

  class Counter2(val count: Int) {
    def inc: Counter2 = inc()
    def dec: Counter2 = dec()
    def inc(amount: Int = 1): Counter2 = new Counter2(this.count + amount)
    def dec(amount: Int = 1): Counter2 = new Counter2(this.count - amount)
  }

  new Counter2(10).inc.dec.inc.inc(4).count

  // 3.1.6.6 Additional Counting
  // Here is a simple class called Adder.
  // Extend Counter to add a method called adjust. This method should accept an Adder
  // and return a new Counter with the result of applying the Adder to the count.

  class Adder(amount: Int) {
    def add(in: Int): Int = in + amount
  }

  class Counter3(val count: Int) {
    def inc: Counter3 = inc()
    def dec: Counter3 = dec()
    def inc(amount: Int = 1): Counter3 = new Counter3(this.count + amount)
    def dec(amount: Int = 1): Counter3 = new Counter3(this.count - amount)
    def adjust(adder: Adder): Counter3 = new Counter3(adder.add(count))
  }

  new Counter3(10).inc.dec.inc.inc(4).adjust(new Adder(4)).count

  // 3.2.3 Exercises
  // 3.2.3.1 When is a Function not a Function?
  // We’ll get a chance to write some code at the end of the next section. For now
  // we should think about an important theoretical question:
  // How close does function application syntax get us to creating truly reusable
  // objects to do computations for us? What are we missing?

  // We're missing types for the functions for portability

  // 3.3.2 Exercises
  // 3.3.2.1 Friendly Person Factory
  // Implement a companion object for Person containing an apply method that
  // accepts a whole name as a single string rather than individual first and last
  // names.

  class Person(val firstName: String, val lastName: String)

  object Person {
    def apply(name: String): Person = {
      val parts = name.split(" ")
      new Person(parts(0), parts(1))
    }
  }

  val bob = Person("Bob Smith")

  // 3.3.2.2 Extended Body of Work
  // Write companion objects for Director and Film as follows:
  // the Director companion object should contain:
  //– an apply method that accepts the same parameters as the constructor of the class and returns a new Director;
  //– a method older that accepts two Directors and returns the oldest of the two.
  //• the Film companion object should contain:
  //– an apply method that accepts the same parameters as the constructor of the class and returns a new Film;
  //– a method highestRating that accepts two Films and returns the highest imdbRating of the two;
  //– a method oldestDirectorAtTheTime that accepts two Films and returns the Director who was oldest at
  //  the respective time of filming.

  // Companion Objects above

  assert(Director.older(eastwood, nolan) == eastwood)
  assert(Film.highestRating(unforgiven, predator) == unforgiven)
  assert(Film.oldestDirectorAtTheTime(unforgiven, predator) == eastwood)

  // 3.3.2.3 Type or Value?
  // The similarity in naming of classes and companion objects tends to cause confusion for new Scala developers.
  // When reading a block of code it is important o know which parts refer to a class or type and which parts
  // refer to a singleton object or value.
  // This is the inspiration for the new hit quiz, Type or Value?, which we will be
  // piloting below. In each case identify whether the word Film refers to the
  // type or value:

  // val prestige: Film = bestFilmByChristopherNolan()
  // type

  // new Film("Last Action Hero", 1993, mcTiernan)
  // type

  // Film("Last Action Hero", 1993, mcTiernan)
  // value

  // Film.newer(highPlainsDrifter, thomasCrownAffair)
  // value

  // Film.type
  // value

  // 3.4.5 Exercises
  // 3.4.5.1 Case Cats
  // Recall that a Cat has a String colour and food. Define a case class to represent a Cat.

  case class Cat2(colour: String, food: String)

  val atreides = Cat2("tabby", "Arms")

  // 3.4.5.2 Roger Ebert Said it Best…
  // No good movie is too long and no bad movie is short enough.
  // The same can’t always be said for code, but in this case we can get rid of a
  // lot of boilerplate by converting Director and Film to case classes. Do this
  // conversion and work out what code we can cut.

  case class Director2(firstName: String, lastName: String, yearOfBirth: Int) {
    def name: String = firstName + " " + lastName
  }

  object Director2 {
    def older(director1: Director2, director2: Director2): Director2 =
      if (director1.yearOfBirth < director2.yearOfBirth) director1 else director2
  }

  case class Film2(name: String, yearOfRelease: Int, imdbRating: Double, director: Director2) {
    def directorsAge: Int = yearOfRelease - director.yearOfBirth
    def isDirectedBy(directedBy: Director2): Boolean = director.name == directedBy.name
  }

  object Film2 {
    def newer(film1: Film2, film2: Film2): Film2 =
      if (film1.yearOfRelease < film2.yearOfRelease) film1 else film2
    def highestRating(film1: Film2, film2: Film2): Film2 =
      if (film1.imdbRating > film2.imdbRating) film1 else film2
    def oldestDirectorAtTheTime(film1: Film2, film2: Film2): Director2 =
      if (film1.directorsAge > film2.directorsAge) film1.director else film2.director
  }

  // 3.4.5.3 Case Class Counter
  //Reimplement Counter as a case class, using copy where appropriate. Additionally initialise count to a default
  // value of 0.

  case class Counter4(count: Int = 0) {
    def inc: Counter4 = inc()
    def dec: Counter4 = dec()
    def inc(amount: Int = 1): Counter4 = this.copy(this.count + amount)
    def dec(amount: Int = 1): Counter4 = this.copy(this.count - amount)
    def adjust(adder: Adder): Counter4 = this.copy(adder.add(count))
  }

  assert(Counter4(10).inc.dec.inc.inc(4).adjust(new Adder(4)).count == 19)

  // 3.4.5.4 Application, Application, Application
  // What happens when we define a companion object for a case class? Let’s see.
  // Take our Person class from the previous section and turn it into a case class
  // (hint: the code is above). Make sure you still have the companion object with
  // the alternate apply method as well.

  case class Person2(firstName: String, lastName: String)

  object Person2 {
    def apply(name: String): Person2 = {
      val parts = name.split(" ")
      new Person2(parts(0), parts(1))
    }
  }

  val bob2 = Person2("Bob Smith")

  // 3.5.3 Exercises
  // 3.5.3.1 Feed the Cats
  // Define an object ChipShop with a method willServe. This method should
  // accept a Cat and return true if the cat’s favourite food is chips, and false otherwise. Use paern matching.

  // case class Cat2 above somewhere

  object ChipShop2 {
    def willServe(cat: Cat2): Boolean = {
      cat match {
        case Cat2(_, "Chips") => true
        case _ => false
      }
    }
  }

  val oswald2 = Cat2(colour = "Black", food = "Milk")
  val henderson2 = Cat2( colour = "Ginger", food = "Chips")
  val quentin2 = Cat2(colour = "Tabby and white", food = "Curry")

  assert(ChipShop2.willServe(oswald2) == false)
  assert(ChipShop2.willServe(henderson2) == true)

  // 3.5.3.2 Get Off My Lawn!
  // In this exercise we’re going to write a simulator of my Dad, the movie critic.
  // It’s quite simple: any movie directed by Clint Eastwood gets a rating 10.0, any
  // movie directed by John McTiernan gets a 7.0, while any other movie gets a 3.0.
  // Implement an object called Dad with a method rate which accepts a Film and
  // returns a Double. Use paern matching.

  // using the Film2 and Director2 case classes
  object Dad {
    def rate(film: Film2): Double = {
      film match {
        case Film2(_, _, _, Director2("Clint", "Eastwood", _)) => 10.0
        case Film2(_, _, _, Director2("John", "McTiernan", _)) => 7.0
        case _ => 3.0
      }
    }
  }

  val eastwood2 = Director2("Clint", "Eastwood", 1930)
  val mcTiernan2 = Director2("John", "McTiernan", 1951)
  val nolan2 = Director2("Christopher", "Nolan", 1970)
  val inception2 = Film2("Inception", 2010, 8.8, nolan2)
  val highPlainsDrifter2 = Film2("High Plains Drifter", 1973, 7.7, eastwood2)
  val predator2 = Film2("Predator", 1987, 7.9, mcTiernan2)

  assert(Dad.rate(inception2) == 3.0)
  assert(Dad.rate(highPlainsDrifter2) == 10.0)
  assert(Dad.rate(predator2) == 7.0)

}

chapter3_exercises
