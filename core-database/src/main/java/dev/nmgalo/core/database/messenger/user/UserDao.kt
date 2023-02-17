package dev.nmgalo.core.database.messenger.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(user: List<User>)

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun getUsersById(userIds: IntArray): List<User>

}
