package errorHandling.oop


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

public interface DomainEventPublisher {
    fun publish(event: DomainEvent)
}