package errorHandling.fp

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import errorHandling.oop.Email
import errorHandling.oop.PhoneNumber
import errorHandling.oop.UserId

interface UserRepository {
    fun findById(id: UserId): Either<Throwable, User>
    fun save(user: User): Either<Throwable, Unit>
}

fun <E> UserRepository.findByOrElse(
    userId: UserId,
    onDoesNotExist: () -> E,
    onUnexpectedError: (Throwable) -> E
): Either<E, User> =
    findById(userId)
        .mapLeft { error ->
            if (error is NoSuchElementException) onDoesNotExist()
            else onUnexpectedError(error)
        }

fun <E> UserRepository.saveOrElse(
    user: User,
    onError: (Throwable) -> E
): Either<E, User> =
    save(user)
        .mapLeft { onError(it) }
        .map { user }

data class User(
    val id: UserId,
    val email: Email,
    val phoneNumber: PhoneNumber,
    val events: List<DomainEvent> = emptyList()
) {
    fun updateEmail(email: Email): Either<UserError, User> =
        if (this.email == email) UserError.UserAlreadyHasGivenEmail.left()
        else copy(email = email).right()
}

sealed class UserError {
    object UserAlreadyHasGivenEmail : UserError()
}