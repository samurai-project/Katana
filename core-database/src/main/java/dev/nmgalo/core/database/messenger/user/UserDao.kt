package dev.nmgalo.core.database.messenger.user

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)
}
