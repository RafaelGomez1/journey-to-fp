package errorHandling.fp

import arrow.core.Either
import errorHandling.oop.Email
import errorHandling.oop.PhoneNumber
import errorHandling.oop.UserId

sealed class DomainEvent

data class UserCreatedEvent(
    val id: UserId,
    val email: Email,
    val phoneNumber: PhoneNumber
) : DomainEvent()

data class UserEmailUpdatedEvent(
    val id: UserId,
    val email: Email
) : DomainEvent()




interface DomainEventPublisher {
    fun publish(event: List<DomainEvent>)
}

fun <E> DomainEventPublisher.publishOrElse(events: List<DomainEvent>, onError: (Throwable) -> E): Either<E, Unit> =
    Either.catch { publish(events) }
        .mapLeft { onError(it) }


