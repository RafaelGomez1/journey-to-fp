package chapters.one.function_Purity

object ReferentiallyTransparentFunctions {

    fun example() {
        val result = square(increment(5)) // 36
    }


    fun increment(value: Int): Int = value.inc()
    fun square(value: Int): Int = value * value
}