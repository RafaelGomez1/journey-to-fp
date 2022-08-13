package chapters.one.higher_order_functions

object ObjectsAsFirstClassCitizens {

    fun example() {
        // Assigned to a variable
        val lebron = Person(name = "Lebron", age = 40)

        // Object passed as an argument
        val lambdaPassed = sayHelloTo(lebron)

        // Object returned from another function
        val underAgePerson = Person.underAge("Bronnie")
    }

    class Person(val name: String, val age: Int) {
        companion object {
            fun underAge(name: String): Person = Person(name = name, age = 16)
        }
    }

    private fun sayHelloTo(person: Person) = println("Hello ${person.name}")

}