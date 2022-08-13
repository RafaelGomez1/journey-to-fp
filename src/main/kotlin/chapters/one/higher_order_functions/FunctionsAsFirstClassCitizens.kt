package chapters.one.higher_order_functions

import java.math.BigDecimal

object FunctionsAsFirstClassCitizens {

    fun example() {

        // Function passed as an argument
        val functionPassed: Int = performOperation(5, ::increment)

        // Lambda passed as an argument
        val lambdaPassed = performOperation(value = 5, operation = { it + 1 })

        // Function returned from another function
        val functionReturn: (Int) -> BigDecimal  = toBigDecimal(5)
    }

    inline fun performOperation(value: Int, operation: (value: Int) -> Int): Int = operation(value)

    fun increment(value: Int): Int = value.inc()

    fun toBigDecimal(value: Int): (Int) -> BigDecimal = { value.toBigDecimal() }
}