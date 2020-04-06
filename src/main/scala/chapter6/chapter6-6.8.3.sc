object chapter6_6_8_3 {
  // 6.8.3.1 Favorites
  // Copy and paste the following code into an editor:
  val people = Set(
    "Alice",
    "Bob",
    "Charlie",
    "Derek",
    "Edith",
    "Fred")
  val ages = Map(
    "Alice" -> 20,
    "Bob" -> 30,
    "Charlie" -> 50,
    "Derek" -> 40,
    "Edith" -> 10,
    "Fred" -> 60)
  val favoriteColors = Map(
    "Bob" -> "green",
    "Derek" -> "magenta",
    "Fred" -> "yellow")
  val favoriteLolcats = Map(
    "Alice" -> "Long Cat",
    "Charlie" -> "Ceiling Cat",
    "Edith" -> "Cloud Cat")

  // Use the code as test data for the following exercises:
  // Write a method favoriteColor that accepts a person’s name as a parameter
  // and returns their favorite colour.
  def favouriteColour(name: String): Option[String] =
    favoriteColors.get(name)

  println("favouriteColour")
  println(favouriteColour("Alice"))
  println(favouriteColour("Bob"))

  // Update favoriteColor to return a person’s favorite color or beige as a default.
  def favouriteColour2(name: String): String =
    favoriteColors.getOrElse(name, "beige")

  println("favouriteColour2")
  println(favouriteColour2("Alice"))
  println(favouriteColour2("Bob"))

  // Write a method printColors that prints everyone’s favorite color!
  println("printColours")

  def printColours(): Unit =
    for {
      person <- people
    } println(s"${person}'s favourite colour is: ${favouriteColour2(person)}")

  printColours()

  // Write a method lookup that accepts a name and one of the maps and returns
  // the relevant value from the map. Ensure that the return type of the method
  // matches the value type of the map.
  def lookup[A](name: String, map: Map[String, A]): Option[A] =
    map.get(name)

  // Calculate the color of the oldest person:
  println("Calculate the color of the oldest person")

  def oldestPerson(): Option[String] =
    people.foldLeft(Option.empty[String]) { (older, person) =>
      if (ages.getOrElse(person, 0) > ages.getOrElse(older.getOrElse(""), 0))
        Some(person)
      else
        older
    }

  val oldest = oldestPerson()
  val favourite: Option[String] =
    for {
      oldest <- oldest
      colour <- favouriteColour(oldest)
    } yield colour
  println(s"The oldest person is ${oldestPerson().getOrElse("")} and their favourite colour is ${favourite.getOrElse("")}")

  // 6.8.4 Do-It-Yourself Part 2
  // Now we have some practice with maps and sets let’s see if we can implement
  // some useful library functions for ourselves.

  // 6.8.4.1 Union of Sets
  // Write a method that takes two sets and returns a set containing the union
  // of the elements. Use iteration, like map or foldLeft, not the built-in union
  // method to do so!

  def setUnion[A](a: Set[A], b: Set[A]): Set[A] =
    a.foldLeft(b) { (acc, curr) =>
      acc + curr
    }

  println("6.8.4.1 Union of Sets")
  val setA = Set("a", "b", "c")
  val setB = Set("b", "c", "d")
  println(setUnion(setA, setB))

  // 6.8.4.2 Union of Maps
  // Now let’s write union for maps. Assume we have two Map[A, Int] and
  // add corresponding elements in the two maps. So the union of Map('a' ->
  // 1, 'b' -> 2) and Map('a' -> 2, 'b' -> 4) should be Map('a' -> 3,
  // 'b' -> 6).

  def mapUnion[A](a: Map[A, Int], b: Map[A, Int]): Map[A, Int] =
    a.foldLeft(b) { (acc, curr) =>
      val (keyA, valueA) = curr
      val valueB = b.getOrElse(keyA, 0)
      val total = valueA + valueB
      acc + (keyA -> total)
    }

  println("6.8.4.2 Union of Maps")
  val mapA = Map('a' -> 1, 'b' -> 2)
  val mapB = Map('a' -> 2, 'b' -> 4)
  println(mapUnion(mapA, mapB))

  // 6.8.4.3 Generic Union
  // There are many things that can be added, such as strings (string concatenation),
  // sets (union), and of course numbers. It would be nice if we could generalise our
  // union method on maps to handle anything for which a sensible add operation
  // can be defined. How can we go about doing this?
  def mapUnionGeneric[A, B](a: Map[A, B], b: Map[A, B], combine: (B, B) => B): Map[A, B] =
    a.foldLeft(b) { (acc, curr) =>
      val (keyA, valueA) = curr
      val combinedValue = b.get(keyA).map(valueB => combine(valueA, valueB)).getOrElse(valueA)
      acc + (keyA -> combinedValue)
    }
}

chapter6_6_8_3
