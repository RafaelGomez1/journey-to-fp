package chapters.one.introduction

object ObjectOrientedProgramming {

    data class EnergyDrink(
        val id: String,
        val name: String,
        val price: Double,
        val availability: Availability = Available
    ) {
        fun create(id: String, name: String, availability: Availability, price: Double): EnergyDrink =
            EnergyDrink(id = id, name = name, availability = availability, price = price)

        fun rename(name: String): EnergyDrink =
            this.copy(name = name)

        fun changePrice(price: Double): EnergyDrink =
            this.copy(price = price)

        fun changeAvailability(availability: Availability): EnergyDrink =
            this.copy(availability = availability)
    }

    sealed class Availability
    object Available : Availability()
    object Unavailable : Availability()
}