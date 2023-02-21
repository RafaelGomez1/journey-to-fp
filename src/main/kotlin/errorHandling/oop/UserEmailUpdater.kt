package errorHandling.oop

class UserEmailUpdater(
    private val repository: UserRepository,
    private val publisher: DomainEventPublisher
) {
    private val finder =  UserFinder(repository)

    fun invoke(id: UserId, email: Email): User =
        finder.invoke(id)
            .let { user -> user.updateEmail(email) }
            .also { user -> repository.save(user) }
            .also { user -> publisher.publish(UserEmailUpdatedEvent(user.id, user.email)) }
}