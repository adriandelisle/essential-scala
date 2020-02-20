class Person(val firstName: String, val lastName: String) {
  def name = firstName + " " + lastName
}

val noel = new Person("Noel", "Welsh")
noel.name
