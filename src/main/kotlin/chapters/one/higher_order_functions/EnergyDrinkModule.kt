package chapters.one.higher_order_functions

import java.util.UUID

object EnergyDrinkModule {

    sealed class Availability
    object Available : Availability()
    object Unavailable : Availability()

    sealed class Drink

    data class EnergyDrink(
        val id: String,
        val name: String,
        val price: Double,
        val availability: Availability = Available
    ) : Drink()

    interface DrinkService<T : Drink> {
        fun create(id: String, name: String, availability: Availability, price: Double): T
        fun changeAvailability(energyDrink: EnergyDrink, availability: Availability): T
        fun rename(energyDrink: EnergyDrink, name: String): T
        fun changePrice(energyDrink: EnergyDrink, price: Double): T
    }

    fun energyDrinkService() = object : DrinkService<EnergyDrink> {
        override fun create(id: String, name: String, availability: Availability, price: Double): EnergyDrink = EnergyDrink(id = id, name = name, availability = availability, price = price)
        override fun rename(energyDrink: EnergyDrink, name: String): EnergyDrink = energyDrink.copy(name = name)
        override fun changePrice(energyDrink: EnergyDrink, price: Double): EnergyDrink = energyDrink.copy(price = price)

        override fun changeAvailability(energyDrink: EnergyDrink, availability: Availability): EnergyDrink = energyDrink.copy(availability = availability)
    }

    // Higher-order function that executes a function and logs it
    inline fun <T : Drink> executeWithLogging(
        drink: T,
        function: (T) -> T,
    ): T = function(drink)
        .also { println("Executed function for ${drink}") }
}