object chapter6_6_10 {
  // 6.10.1 Random Words
  // We’ll start by generating text. Imagine we wanted to generate (somewhat)
  // realistic text, perhaps to use as a placeholder to fill in parts of a website design.
  // If we took a large amount of real text we could analyse to work out for each
  // word what the most common words following it are. Such a model is known
  // as a Markov chain.
  // To keep this example to a reasonable size we’re going to deal with a really
  // simplified version of the problem, where all sentences have the form subjectverb-object. For example, “Noel wrote code”.
  // Write a program to generate all possible sentences given the following model:
  //  • subjects are List("Noel", "The cat", "The dog");
  //  • verbs are List("wrote", "chased", "slept on"); and
  //  • objects are List("the book", "the ball", "the bed").
  println("6.10.1 Random Words")
  val subjects = List("Noel", "The cat", "The dog")
  val verbs = List("wrote", "chased", "slept on")
  val objects = List("the book", "the ball", "the bed")
  val randomWordsTake1 = for {
    subject <- subjects
    verb <- verbs
    obj <- objects
  } yield s"$subject $verb $obj"
  println(randomWordsTake1)

  // This model creates some clearly nonsensical sentences. We can do beer by
  // making the choice of verb dependend on the subject, and the object depend
  // on the verb.
  // Let’s use the following model:
  //  • The subjects are as before.
  //  • For the verbs:
  //  • If the subject is “Noel” the possible verbs are “wrote”, “chased”, and “slept on”.
  //  • If the subject is “The cat” the possible verbs are “meowed at”, “chased”, and “slept on”.
  //  • If the subject is “The dog” the possible verbs are “barked at”, “chased”, and “slept on”.
  //  • For the objects:
  //  • If the verb is “wrote” the possible objects are “the book”, “the letter”, and “the code”.
  //  • If the verb is “chased” the possible objects are “the ball”, “the dog”, and “the cat”.
  //  • If the verb is “slept on” the possible objects are “the bed”, “the mat”, and “the train”.
  //  • If the verb is “meowed at” the possible objects are “Noel”, “the door”, “the food cupboard”.
  //  • If the verb is “barked at” the possible objects are “the postman”, “the car”, and “the cat”

  // Implement this.

  def verbsForSubject(subject: String): List[String] =
    subject match {
      case "Noel" => List("wrote", "chased", "slept on")
      case "The cat" => List("meowed at", "chased", "slept on")
      case "The dog" => List("barked at", "chased", "slept on")
    }

  def objectsForVerb(verb: String): List[String] =
    verb match {
      case "wrote" => List("the book", "the letter", "the code")
      case "chased" => List("the ball", "the dog", "the cat")
      case "slept on" => List("the bed", "the mat", "the train")
      case "meowed at" => List("Noel", "the door", "the food cupboard")
      case "barked at" => List("the postman", "the car", "the cat")
    }

  val randomWordsTake2 = for {
    subject <- subjects
    verb <- verbsForSubject(subject)
    obj <- objectsForVerb(verb)
  } yield s"$subject $verb $obj"
  println(randomWordsTake2)

  // 6.10.2 Probabilities
  // We now have a model that we can imagine making arbitrarily complex to generate more and more realistic data,
  // but we’re missing the element of probability that would allow us to weight the data generation towards more common
  // outcomes.
  // Let’s extend our model to work on List[(A, Double)], where A is the type
  // of data we are generating and the Double is a probability. We’re still enumerating all possibilities but we’re
  // now associating a probability with each possible outcome.
  // Start by defining a class Distribution that will wrap a List[(A, Double)].
  // (Why?)
  final case class Distribution[A](events: List[(A, Double)]) {
    def map[B](f: A => B): Distribution[B] =
      Distribution(events.map{
        case (a, p) => f(a) -> p
      })

    def flatMap[B](f: A => Distribution[B]): Distribution[B] =
      Distribution(events.flatMap{
        case (a, p1) =>
          f(a).events.map {
            case (b, p2) => b -> (p1 * p2)
          }
      }).compact.normalize

    def normalize: Distribution[A] = {
      val totalWeight = (events map { case (a, p) => p }).sum
      Distribution(events map { case (a,p) => a -> (p / totalWeight) })
    }

    def compact: Distribution[A] = {
      val distinct = (events map { case (a, p) => a }).distinct
      def prob(a: A): Double =
        (events filter { case (x, p) => x == a } map { case (a, p) => p
        }).sum
      Distribution(distinct map { a => a -> prob(a) })
    }

  }

  // We should create some convenience constructors for Distribution.
  // A useful one is uniform which will accept a List[A] and create a
  // Distribution[A] where each element has equal probability. Make it
  // so.
  object Distribution {
    def uniform[A](list: List[A]): Distribution[A] =
      Distribution(list.map(el => (el -> 1.0 / list.size)))

    def discrete[A](events: List[(A,Double)]): Distribution[A] =
      Distribution(events).compact.normalize
  }

  // What are the other methods we must add to implement the models we’ve
  // seen so far? What are their signatures?
  // map and flatMap
  // def map[B](f: A => B): Distribution[B] = ???
  // def flatMap[B](f: A => Distribution[B]): Distribution[B] = ???

  // Now implement these methods. Start with map, which is simpler. We might
  // end up with elements appearing multiple times in the list of events after calling
  // map. That’s absolutely ok.

  // 6.10.3 Examples
  // With Distribution we can now define some interesting model. We could
  // do some classic problems, such as working out the probability that a coin flip
  // gives three heads in a row.
  println("6.10.3 Examples")
  sealed trait Coin
  case object Heads extends Coin
  case object Tails extends Coin

  val fairCoin: Distribution[Coin] = Distribution.uniform(List(Heads, Tails))

  val threeFlips =
    for {
      c1 <- fairCoin
      c2 <- fairCoin
      c3 <- fairCoin
    } yield (c1, c2, c3)
  println(threeFlips)

  // From this we can read of the probability of three heads being 0.125, as we’d expect.
  // Let’s create a more complex model. Imagine the following situation:
  // I put my food into the oven and after some time it ready to eat and produces
  // delicious smell with probability 0.3 and otherwise it is still raw and produces
  // no smell with probability 0.7. If there are delicious smells the cat comes to
  // harass me with probability 0.8, and otherwise it stays asleep. If there is no
  // smell the cat harasses me for the hell of it with probability 0.4 and otherwise
  // stays asleep.
  // Implement this model and answer the question: if the cat comes to harass me
  // what is the probability my food is producing delicious smells (and therefore is
  // ready to eat.)
  // I found it useful to add this constructor to the companion object of
  // Distribution:

  sealed trait Food
  case object Raw extends Food
  case object Cooked extends Food

  val foodCooked: Distribution[Food] = Distribution(List(Raw -> 0.7, Cooked -> 0.3))

  sealed trait Cat
  case object Asleep extends Cat
  case object Harass extends Cat

  def cat(foodCooked: Food): Distribution[Cat] =
    foodCooked match {
      case Raw => Distribution.discrete(List(Harass -> 0.4, Asleep -> 0.6))
      case Cooked => Distribution.discrete(List(Harass -> 0.8, Asleep -> 0.2))
    }

  val foodModel: Distribution[(Food, Cat)] =
    for  {
      f <- foodCooked
      c <- cat(f)
    } yield (f, c)
  println(foodModel)

  val pHarassing: Double =
    foodModel.events.filter {
      case ((_, Harass), _) => true
      case ((_, Asleep), _) => false
    }.map { case (a, p) => p }.sum
  println(pHarassing)

  val pCookedGivenHarassing: Option[Double] =
    foodModel.events collectFirst[Double] {
      case ((Cooked, Harass), p) => p
    } map (_ / pHarassing)
  println(pCookedGivenHarassing)
}

chapter6_6_10
