package chapters.one.higher_order_functions

object HigherOrderBenefits {

    fun example() {
        val drinks = listOf(
            EnergyDrink(name = "RedBull", available = false),
            EnergyDrink(name = "Monster", available = true),
            EnergyDrink(name = "MountainDew", available = false),
            EnergyDrink(name = "Dr.Pepper", available = true)
        )

        val availableDrinks = drinks.filter { drink -> drink.available }
        val drinksStartingWithMo = drinks.filter { drink -> drink.name.startsWith("Mo") }
        val drinksStartingWithDr = drinks.filter { drink -> drink.name.startsWith("Dr") }
    }

    data class EnergyDrink(val name: String, val available: Boolean)
}

