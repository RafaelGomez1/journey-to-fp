package errorHandling.fp

import arrow.core.Either
import arrow.core.flatMap
import errorHandling.oop.Email
import errorHandling.oop.UserId

class UserEmailUpdater(
    private val repository: UserRepository,
    private val publisher: DomainEventPublisher
) {

    fun invoke(id: UserId, email: Email): Either<UpdateUserEmailError, Unit> =
        repository.findByOrElse(
            userId = id,
            onDoesNotExist = { UpdateUserEmailError.UserDoesNotExist },
            onUnexpectedError = { error -> UpdateUserEmailError.Unknown(error) }
        )
            .flatMap { user -> user.changeEmail(email) }
            .flatMap { user -> repository.saveOrElse(user = user, onError = { error -> UpdateUserEmailError.Unknown(error) }) }
            .flatMap { user -> publisher.publishOrElse(events = user.events, onError = { error -> UpdateUserEmailError.Unknown(error) }) }

    private fun User.changeEmail(email: Email) =
        updateEmail(email)
            .mapLeft { error ->
                when(error) {
                    is UserError.UserAlreadyHasGivenEmail -> UpdateUserEmailError.UserAlreadyHasGivenEmail
                }
            }
}

sealed class UpdateUserEmailError {
    object UserDoesNotExist : UpdateUserEmailError()
    object UserAlreadyHasGivenEmail : UpdateUserEmailError()
    class Unknown(val error: Throwable) : UpdateUserEmailError()
}


