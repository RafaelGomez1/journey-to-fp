package chapters.one.function_Purity

import chapters.one.higher_order_functions.EnergyDrinkModule
import java.security.InvalidParameterException
import java.util.UUID

object ThrowingExceptionExample {

    fun example() {
        val drink = EnergyDrink(
            id = UUID.randomUUID().toString(),
            name = "RedBull",
            availability = EnergyDrinkModule.Unavailable,
            price = 1.35
        )

        val renamedDrink = drink.rename("           ")

        println("The renamed drink is ${renamedDrink.name}")
    }

    fun EnergyDrink.rename(name: String): EnergyDrink {
        if (name.isBlank()) throw InvalidParameterException()

        return this.copy(name = name)
    }

    data class EnergyDrink(
        val id: String,
        val name: String,
        val price: Double,
        val availability: EnergyDrinkModule.Availability = EnergyDrinkModule.Available
    )
}