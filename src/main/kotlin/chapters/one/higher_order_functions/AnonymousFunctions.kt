package chapters.one.higher_order_functions

object AnonymousFunctions {

    fun example() {
        // Square function as a lambda expression
        val square = { value : Int -> value * value }
        square(5)

        // Square function as an anonymous function
        val squareAnonymous = fun ( value: Int ): Int = value * value
        squareAnonymous(5)

        // usage of both functions
        performOperation(5, square)
        performOperation(5, squareAnonymous)
    }

    private inline fun performOperation(
        value: Int,
        operation: (value: Int) -> Int
    ): Int = operation(value)
}