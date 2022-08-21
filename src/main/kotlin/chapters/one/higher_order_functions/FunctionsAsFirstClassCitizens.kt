package chapters.one.higher_order_functions

import java.math.BigDecimal

object FunctionsAsFirstClassCitizens {

    fun example() {
        // Function passed as an argument
        val increment : Int = performOperation(5, ::increment)
        val decrement : Int = performOperation(5, ::decrement)
        val square : Int = performOperation(5, ::square)

        // Lambda passed as an argument
        val lambdaPassed = performOperation(value = 5, operation = { it + 1 } )

        // Function returned from another function
        val functionReturn: (Int) -> BigDecimal  = toBigDecimal(5)
    }

    private inline fun performOperation(value: Int, operation: (value: Int) -> Int): Int = operation(value)

    private fun increment(value: Int): Int = value.inc()
    private fun decrement(value: Int): Int = value.dec()
    private fun square(value: Int): Int = value * value

    private fun toBigDecimal(value: Int): (Int) -> BigDecimal = { value.toBigDecimal() }
}