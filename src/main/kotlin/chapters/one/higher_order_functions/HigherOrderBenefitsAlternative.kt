package chapters.one.higher_order_functions

import java.util.UUID

object HigherOrderBenefitsAlternative {
    fun example() {
        // Generation of the service
        val energyDrink = EnergyDrink(id = UUID.randomUUID().toString(), name = "RedBull")
        val energyDrinkService = energyDrinkService(energyDrink)

        // Sample drink list
        val drinks = listOf(
            energyDrinkService.create(name = "RedBull", availability = Unavailable),
            energyDrinkService.create(name = "Monster", availability = Available),
            energyDrinkService.create(name = "MountainDew", availability = Unavailable),
            energyDrinkService.create(name = "Dr.Pepper", availability = Available)
        )

        // Filtering by predicates using higher-order function filter
        val allAvailableDrinks = drinks.filter { drink -> drink.availability == Available }
        val drinksStartingWithMo = drinks.filter { drink -> drink.name.startsWith("Mo") }
        val drinksStartingWithDr = drinks.filter { drink -> drink.name.startsWith("Dr") }

        // Executing EnergyDrink service methods inside higher-order function executeWithLogging
        val unavailableEnergyDrink = executeWithLogging(
            energyDrink = energyDrink,
            funtion = { drink ->  energyDrinkService.changeAvailability(Unavailable) }
        )

        val renamedEnergyDrink = executeWithLogging(
            energyDrink = energyDrink,
            funtion = { drink ->  energyDrinkService.rename("Coca-Cola") }
        )

        val availableEnergyDrink = executeWithLogging(
            energyDrink = energyDrink,
            funtion = { drink ->  energyDrinkService.changeAvailability(Available) }
        )

    }

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
        fun changeAvailability(availability: Availability): T
        fun rename(name: String): T
    }

    // Typically you'd have the dependencies of the Energy Module here (repositories, eventPublishers) instead of energy drink object
    private fun energyDrinkService(energyDrink: EnergyDrink) = object : EnergyDrinkService<EnergyDrink> {
        override fun create(name: String, availability: Availability): EnergyDrink = EnergyDrink(id = UUID.randomUUID().toString(),name = name, availability = availability)
        override fun rename(name: String): EnergyDrink = energyDrink.copy(name = name)
        override fun changeAvailability(availability: Availability): EnergyDrink = energyDrink.copy(availability = availability)
    }

    // Higher-order function that executes a function and logs it
    inline fun <T : Drink> executeWithLogging(
        energyDrink: T,
        funtion: (T) -> T,
    ): T = funtion(energyDrink)
        .also { println("Executed function for ${energyDrink}") }
}