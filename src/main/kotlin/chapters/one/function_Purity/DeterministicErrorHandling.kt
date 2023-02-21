package chapters.one.function_Purity

import chapters.one.higher_order_functions.EnergyDrinkModule
import java.security.InvalidParameterException
import java.util.*

object DeterministicErrorHandling {

    fun example() {
        val drink = EnergyDrink(
            id = UUID.randomUUID().toString(),
            name = "RedBull",
            availability = EnergyDrinkModule.Unavailable,
            price = 1.35
        )

        val renamedDrink: Result = drink.rename("Monster")

        when (renamedDrink) {
            is Result.Success -> println("The renamed drink is ${renamedDrink.energyDrink.name}")
            is Result.Failure -> println("Execution failed due to ${renamedDrink.reason}}")
        }
    }

    fun EnergyDrink.rename(name: String): Result =
        if (name.isBlank()) Result.Failure(reason = InvalidParameterException())
        else Result.Success(energyDrink = this.copy(name = name))

    data class EnergyDrink(
        val id: String,
        val name: String,
        val price: Double,
        val availability: EnergyDrinkModule.Availability = EnergyDrinkModule.Available
    )

    sealed class Result() {
        class Success(val energyDrink: EnergyDrink) : Result()
        class Failure(val reason: RuntimeException) : Result()
    }
}