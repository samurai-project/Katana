package dev.nmgalo.core.data.user

import dev.nmgalo.core.common.Dispatcher
import dev.nmgalo.core.common.KatanaDispatchers
import dev.nmgalo.core.model.user.User
import dev.nmgalo.core.network.model.users.UserDTO
import dev.nmgalo.core.network.wall.WallNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val network: WallNetworkDataSource,
    @Dispatcher(KatanaDispatchers.IO)
    private val io: CoroutineDispatcher
) : UserRepository {

    override fun getUserById(userId: Long): Flow<User> = flow {
        emit(network.getUserById(userId).toDomainModel())
    }.flowOn(io)

    override fun getAllUser(): Flow<List<User>> = flow {
        emit(
            network.getAllUser().map {
                it.toDomainModel()
            }
        )
    }.flowOn(io)

    private fun UserDTO.toDomainModel() = User(
        id = this.id,
        username = this.username,
        phone = this.phone,
        email = this.email
    )
}
