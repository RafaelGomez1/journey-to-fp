package chapters.one.higher_order_functions

object LambdaExample {

    fun example() {
        val list = (1..10).map { value -> value * value }

        val filteredList = list.filter { value -> value > 10 }
    }
}