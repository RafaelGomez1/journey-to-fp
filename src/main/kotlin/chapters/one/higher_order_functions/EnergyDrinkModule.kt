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
        val availability: Availability = Available
    ) : Drink()

    interface EnergyDrinkService<T : Drink> {
        fun create(name: String, availability: Availability): T
        fun changeAvailability(energyDrink: EnergyDrink, availability: Availability): T
        fun rename(energyDrink: EnergyDrink, name: String): T
    }

    fun energyDrinkService() = object : EnergyDrinkService<EnergyDrink> {
        override fun create(name: String, availability: Availability): EnergyDrink = EnergyDrink(id = UUID.randomUUID().toString(),name = name, availability = availability)
        override fun rename(energyDrink: EnergyDrink, name: String): EnergyDrink = energyDrink.copy(name = name)
        override fun changeAvailability(energyDrink: EnergyDrink, availability: Availability): EnergyDrink = energyDrink.copy(availability = availability)
    }

    // Higher-order function that executes a function and logs it
    inline fun <T : Drink> executeWithLogging(
        drink: T,
        funtion: (T) -> T,
    ): T = funtion(drink)
        .also { println("Executed function for ${drink}") }
}