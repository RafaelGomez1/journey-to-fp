package chapters.one.higher_order_functions

import chapters.one.higher_order_functions.EnergyDrinkModule.energyDrinkService
import chapters.one.higher_order_functions.EnergyDrinkModule.executeWithLogging
import chapters.one.higher_order_functions.EnergyDrinkModule.Unavailable
import chapters.one.higher_order_functions.EnergyDrinkModule.Available


object HigherOrderBenefitsAlternative {

    fun filteringExample() {
        // Generation of the service
        val energyDrinkService = energyDrinkService()

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
    }

    fun executeWithLoggingExample() {
        // Generation of the service
        val energyDrinkService = energyDrinkService()

        // Executing EnergyDrink service methods inside higher-order function executeWithLogging
        val energyDrink = energyDrinkService.create(name = "RedBull", availability = Available)

        val unavailableEnergyDrink = executeWithLogging(
            energyDrink = energyDrink,
            funtion = { drink ->  energyDrinkService.changeAvailability(drink, Unavailable) }
        )

        val renamedEnergyDrink = executeWithLogging(
            energyDrink = energyDrink,
            funtion = { drink ->  energyDrinkService.rename(drink, "Coca-Cola") }
        )

        val availableEnergyDrink = executeWithLogging(
            energyDrink = energyDrink,
            funtion = { drink ->  energyDrinkService.changeAvailability(drink, Available) }
        )

    }
}