package errorHandling.fp

import arrow.core.Either
import errorHandling.oop.UserId

class UserFinder(private val repository: UserRepository) {

    fun invoke(id: UserId): Either<FindUserError, User> =
        repository.findById(id)
            .mapLeft { error ->
                when(error) {
                    is NoSuchElementException -> FindUserError.UserDoesNotExist
                    else -> FindUserError.Unknown(error)
                }
            }
}

sealed class FindUserError {
    object UserDoesNotExist : FindUserError()
    class Unknown(val error: Throwable) : FindUserError()
}

