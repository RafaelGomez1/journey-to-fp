package optics

import arrow.optics.Every
import arrow.optics.Fold
import arrow.optics.Traversal

object Foo {

    val paymentConcept = Concept(
        type = ConceptType.CARD,
        status = Status.PAID
    )

    val paymentConcept2 = Concept(
        type = ConceptType.CREDIT,
        status = Status.PAID
    )

    val refundConcept = Concept(
        type = ConceptType.CARD,
        status = Status.PROCESSING
    )

    val refundConcept2 = Concept(
        type = ConceptType.CREDIT,
        status = Status.PROCESSING
    )

    val paymentOperation = Operation(
        type = OperationType.PAYMENT,
        status = Status.PAID,
        concepts = listOf(paymentConcept, paymentConcept2)
    )

    val refundOperation = Operation(
        type = OperationType.REFUND,
        status = Status.PROCESSING,
        concepts = listOf(refundConcept, refundConcept2)
    )

    val payment = Payment(operations = listOf(paymentOperation, refundOperation))

    fun invoke() {
        // Iterate over list of operations and select the refund operation type
        val a: Every<Payment, List<Operation>> =
            Payment.operations
                .every(Every.list<Operation>())
                .


        Instruments.instruments.every(Every.list()).guitar.strings.modify(instruments){ GuitarString.newStrings() }

        val everyConcept: Every<List<Concept>, Concept> = Every.from(Traversal.list(), Fold.list())
        val everyOperation: Every<List<Operation>, Operation> = Every.from(Traversal.list(), Fold.list())


        payment.operations.

    }
}