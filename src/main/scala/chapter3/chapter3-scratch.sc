object chapter3_scratch {

  class Person(val firstName: String, val lastName: String) {
    def name = firstName + " " + lastName
  }

  val noel = new Person("Noel", "Welsh")
  println(noel.name)

}

chapter3_scratch
