package chapters.one.higher_order_functions

object LambdaExpressions {

    fun example() {
        // Lambda passed as an argument
        val increaseValueFunction = performOperation(value = 5, operation = ::increase)
        val increaseValueLambda = performOperation(value = 5, operation = { it + 1 })

        val squareValueFunction = performOperation(value = 5, operation = ::square)
        val squareValueLambda = performOperation(value = 5, operation = { it * it })

        // Lambda passed as trailing
        val squareValueTrailingLambda = performOperation(value = 5) { it * it }
    }

    private fun increase(value: Int) = value + 1
    private fun square(value: Int) = value * value

    private inline fun performOperation(
        value: Int,
        operation: (value: Int) -> Int
    ): Int = operation(value)
}