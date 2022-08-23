package chapters.one.higher_order_functions

object Closures {

    fun example() {
        val peter = Person(name = "Peter", age = 25)
        val minimumDrinkingAge = 21

        peter.also {
            if (it.age > minimumDrinkingAge)
                println("I can finally have a beer")
            else println("Oooh please nobody has to know")
        }
    }

    class Person(val name: String, val age: Int) {
        companion object {
            fun underAge(name: String): Person = Person(name = name, age = 16)
        }
    }
}
