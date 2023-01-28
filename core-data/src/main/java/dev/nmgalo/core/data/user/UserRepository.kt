package dev.nmgalo.core.data.user

import dev.nmgalo.core.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUserById(userId: Long): Flow<User>

    fun getAllUser(): Flow<List<User>>
}
