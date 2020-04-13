object appendix_b_B_6_4 {
  // B.6.4.1 Animals
  // Create a Seq containing the Strings "cat", "dog", and "penguin". Bind it
  // to the name animals.
  val animals = Seq("cat", "dog", "penguin")
  println(animals)
  // Append the element "tyrannosaurus" to animals and prepend the element
  // "mouse".
  println("mouse" +: animals :+ "tyrannosaurus")

  // What happens if you prepend the Int 2 to animals? Why? Try it out… were
  // you correct?
  // It will be a List with 2 at the end
  println(animals :+ 1)

  // Now create a mutable sequence containing "cat", "dog", and "penguin"
  // and update an element to be an Int. What happens?
  val mutableAnimals = scala.collection.mutable.Seq("cat", "dog", "penguin")
  println(mutableAnimals)
  //  mutableAnimals.update(0, 1)
  // there is a type mismatch
  
}

appendix_b_B_6_4
