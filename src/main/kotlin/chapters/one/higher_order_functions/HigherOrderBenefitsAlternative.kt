package chapters.one.higher_order_functions

import chapters.one.higher_order_functions.EnergyDrinkModule.energyDrinkService
import chapters.one.higher_order_functions.EnergyDrinkModule.executeWithLogging
import chapters.one.higher_order_functions.EnergyDrinkModule.Unavailable
import chapters.one.higher_order_functions.EnergyDrinkModule.Available
import java.util.UUID


object HigherOrderBenefitsAlternative {

    fun filteringExample() {
        // Generation of the service
        val energyDrinkService = energyDrinkService()

        // Sample drink list
        val drinks = listOf(
            energyDrinkService.create(id = UUID.randomUUID().toString(), name = "RedBull", availability = Unavailable, price = 1.35),
            energyDrinkService.create(id = UUID.randomUUID().toString(), name = "Monster", availability = Available, price = 1.50),
            energyDrinkService.create(id = UUID.randomUUID().toString(), name = "MountainDew", availability = Unavailable, price = 1.20),
            energyDrinkService.create(id = UUID.randomUUID().toString(), name = "Dr.Pepper", availability = Available, price = 1.15)
        )

        // Filtering by predicates using higher-order function filter
        val allAvailableDrinks = drinks.filter { drink -> drink.availability == Available }
        val drinksStartingWithMo = drinks.filter { drink -> drink.name.startsWith("Mo") }
        val drinksStartingWithDr = drinks.filter { drink -> drink.name.startsWith("Dr") }
        val drinksUnder130 = drinks.filter { drink -> drink.price <= 1.30 }
    }

    fun executeWithLoggingExample() {
        // Generation of the service
        val energyDrinkService = energyDrinkService()

        // Executing EnergyDrink service methods inside higher-order function executeWithLogging
        val energyDrink = energyDrinkService.create(id = UUID.randomUUID().toString(), name = "RedBull", availability = Available, price = 2.20)

        val unavailableEnergyDrink = executeWithLogging(
            drink = energyDrink,
            function = { drink ->  energyDrinkService.changeAvailability(drink, Unavailable) }
        )

        val renamedEnergyDrink = executeWithLogging(
            drink = energyDrink,
            function = { drink ->  energyDrinkService.rename(drink, "Coca-Cola") }
        )

        val availableEnergyDrink = executeWithLogging(
            drink = energyDrink,
            function = { drink ->  energyDrinkService.changeAvailability(drink, Available) }
        )

        val updatedPriceEnergyDrink = executeWithLogging(
            drink = energyDrink,
            function = { drink -> energyDrinkService.changePrice(drink, 1.20)}
        )

    }
}