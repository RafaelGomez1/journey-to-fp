package errorHandling.fp

import arrow.core.Either
import arrow.core.flatMap
import errorHandling.oop.Email
import errorHandling.oop.UserId

class UserEmailUpdater3(
    private val repository: UserRepository,
    private val publisher: DomainEventPublisher
) {
    private val finder =  UserFinder(repository)

    fun invoke(id: UserId, email: Email): Either<UpdateUserEmailError2, Unit> =
        findUser(id)
            .flatMap { user -> user.changeEmail(email) }
            .flatMap { user -> save(user) }
            .flatMap { user -> publishEvents(user) }

    private fun findUser(userId: UserId) =
        finder.invoke(userId)
            .mapLeft { error ->
                when(error) {
                    is FindUserError.UserDoesNotExist -> UpdateUserEmailError2.UserDoesNotExist2
                    is FindUserError.Unknown -> UpdateUserEmailError2.Unknown(error.error)
                }
            }

    private fun User.changeEmail(email: Email) =
        updateEmail(email)
            .mapLeft { error ->
                when(error) {
                    is UserError.UserAlreadyHasGivenEmail -> UpdateUserEmailError2.UserAlreadyHasGivenEmail2
                }
            }

    private fun save(user: User) =
        repository.save(user)
            .map { user }
            .mapLeft { error -> UpdateUserEmailError2.Unknown(error) }

    private fun publishEvents(user: User) =
        Either.catch { publisher.publish(user.events) }
            .mapLeft { error -> UpdateUserEmailError2.Unknown(error) }
}

sealed class UpdateUserEmailError2 {
    object UserDoesNotExist2 : UpdateUserEmailError2()
    object UserAlreadyHasGivenEmail2 : UpdateUserEmailError2()
    class Unknown(val error: Throwable) : UpdateUserEmailError2()
}
