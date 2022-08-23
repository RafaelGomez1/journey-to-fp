package chapters.one.higher_order_functions

object ExpressionsVsStatements {

    fun example() {

        // Different types of expressions
        val simpleExpression = 1 + 1
        val constantExpression = 1 * OFFSET
        val commonExpression = { a: Int, b: Int -> if( a > b) a else b }
        commonExpression(2, 3 * OFFSET)

        // Different types of statements
        var a = 90
        var b = 10

        while (a > 25) {
            b += 10
            a.dec()
        }

    }

    private const val OFFSET = 5
}