package errorHandling.oop

class UserFinder(private val repository: UserRepository) {

    fun invoke(id: UserId): User =
        repository.findById(id)
            .orElseThrow { UserDoesNotExistException(id) }
}