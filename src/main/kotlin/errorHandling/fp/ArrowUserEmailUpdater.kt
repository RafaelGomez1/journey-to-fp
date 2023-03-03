package errorHandling.fp

import arrow.core.Either
import arrow.core.flatMap
import errorHandling.oop.Email
import errorHandling.oop.UserId

class UserEmailUpdater5(
    private val repository: UserRepository,
    private val publisher: DomainEventPublisher
) {
    private val finder =  UserFinder(repository)

    fun invoke(id: UserId, email: Email): Either<ArrowUpdateUserEmailError, Unit> =
        findUser(id)
            .flatMap { user -> user.changeEmail(email) }
            .flatMap { user -> save(user) }
            .flatMap { user -> publishEvents(user) }

    private fun findUser(userId: UserId) =
        finder.invoke(userId)
            .mapLeft { error ->
                when(error) {
                    is FindUserError.UserDoesNotExist -> ArrowUpdateUserEmailError.UserDoesNotExistArrow
                    is FindUserError.Unknown -> ArrowUpdateUserEmailError.Unknown(error.error)
                }
            }

    private fun User.changeEmail(email: Email) =
        updateEmail(email)
            .mapLeft { error ->
                when(error) {
                    is UserError.UserAlreadyHasGivenEmail -> ArrowUpdateUserEmailError.UserAlreadyHasGivenEmailArrow
                }
            }

    private fun save(user: User) =
        repository.save(user)
            .map { user }
            .mapLeft { error -> ArrowUpdateUserEmailError.Unknown(error) }

    private fun publishEvents(user: User) =
        Either.catch { publisher.publish(user.events) }
            .mapLeft { error -> ArrowUpdateUserEmailError.Unknown(error) }
}

sealed class ArrowUpdateUserEmailError {
    object UserDoesNotExistArrow : ArrowUpdateUserEmailError()
    object UserAlreadyHasGivenEmailArrow : ArrowUpdateUserEmailError()
    class Unknown(val error: Throwable) : ArrowUpdateUserEmailError()
}
