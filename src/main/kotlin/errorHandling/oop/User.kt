package errorHandling.oop

import java.util.*

typealias UserId = UUID
typealias Email = String
typealias PhoneNumber = Int

data class User(
    val id: UserId,
    val email: Email,
    val phoneNumber: PhoneNumber
) {
    fun updateEmail(email: Email) =
        if (this.email == email) throw UserAlreadyHasEmailException(email)
        else copy(email = email)
}

public interface UserRepository {
    fun findById(id: UserId): Optional<User>
    fun save(user: User)
}

class UserDoesNotExistException(id: UserId) : Throwable()
class UserAlreadyHasEmailException(email: Email) : Throwable()