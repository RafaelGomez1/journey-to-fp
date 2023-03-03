package optics

import arrow.optics.optics

@optics
data class Payment(
    val operations: List<Operation>
) {
    companion object
}

@optics
data class Operation(
    val type: OperationType,
    val status: Status,
    val concepts: List<Concept>
) {
    companion object
}

@optics
data class Concept(
    val type: ConceptType,
    val status: Status
) { companion object }

enum class Status {
    PROCESSING, PAID, FAILED
}

@optics
sealed class OperationType {

    object PAYMENT : OperationType()
    object REFUND : OperationType()
    companion object {}
}

enum class ConceptType {
    CREDIT, CARD;
}

typealias Money = Int
