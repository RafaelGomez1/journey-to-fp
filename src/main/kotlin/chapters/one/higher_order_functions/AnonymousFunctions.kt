package chapters.one.higher_order_functions

object AnonymousFunctions {

    fun example() {
        // Lambda passed as an argument
        val increaseValue = performOperation(value = 5, operation = { it + 1 })
        val squareValue = performOperation(value = 5, operation = { it * it })

        // Lambda passed as trailing
        val squareValueTrailing = performOperation(value = 5) { it * it }
    }

    private inline fun performOperation(
        value: Int,
        operation: (value: Int) -> Int
    ): Int = operation(value)
}