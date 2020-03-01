"Hello world!"

"Hello world!".toUpperCase()

42f

42L

object Test2 {
  def name: String = "Probably the best object ever"
}

Test2.name

object Test3 {
  def hello(name: String) =
    "Hello " + name
}

Test3.hello("Bob")

object Test4 {
  val name = "Noel"

  def hello(other: String): String =
    name + " says hi to " + other
}

Test4.hello("Jim")