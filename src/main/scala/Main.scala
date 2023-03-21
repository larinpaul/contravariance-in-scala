object Main {

  // The known part
  val list: List[Int] = List(1,2,3)

  // The unknown part, the piece of generics, is variance

  class Animal
  class Dog(name: String) extends Animal

  // Question: if Dog <: Animal, does List[Dog] <: List[Animal]? THE VARIANCE QUESTION.

  // If YES, then the type is called COVARIANT
  // In Scala, we denote that by putting a +
  val lassie = new Dog("Lassie")
  val hachi = new Dog("Hachi")
  val laika = new Dog("Laika")

  val anAnimal: Animal = lassie // dog
  val myDogs: List[Animal] = List(lassie, hachi, laika) // A list of dogs is a list of animals

  // If NO, then the type is INVARIANT // Java also deals with this by default by assuming it's INVARIANT
  class MyInvariantList[T]
  //  val myDogs: MyInvariantList[Animal] = new MyInvariantList[Dog] // will not compile
  val myAnimals: MyInvariantList[Animal] = new MyInvariantList[Animal]

  // HELL NO, or No, quite the opposite - CONTRAVARIANCE
  class MyContravariantList[-T] // Contravariant type, will transfer the type in the opposite direction
  val myDogs2: MyContravariantList[Dog] = new MyContravariantList[Animal] // ?!

  // a contravariance example
  trait Vet[-T/* <: Animal*/] {
    def heal(animal: T): Boolean
  }

  def gimmeAVet(): Vet[Dog] = new Vet[Animal] {
    override def heal(animal: Animal) = {
      println("You'll be fine")
      true
    }
  }

  val myDog = new Dog("Buddy")
  val myVet: Vet[Dog] = gimmeAVet()
  myVet.heal(myDog) // Buddy is happy and healthy

  // Co- vs Contravariance

  // Not every concept can be applicable to co or contravariance

  // Rule of thumb
  // * if your generic type contains or crates elements of type T, it should be +T
  // * if your generic type acts on or consumes elements of type T, it should be -T

  // Examples
  // * covariant concepts: a cage, a garage, a factory, a list
  // * contravariant concepts: a vet, a mechanic, a garbage pit, a function (in terms of arg type)

  def main(args: Array[String]): Unit = {
    println("Hello world!")
  }
}
