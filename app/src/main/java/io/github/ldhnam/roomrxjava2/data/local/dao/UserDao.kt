package io.github.ldhnam.roomrxjava2.data.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.github.ldhnam.roomrxjava2.data.model.User
import io.reactivex.Flowable

@Dao
interface UserDao {

    @Query("SELECT * FROM user ORDER BY created_at DESC")
    fun getAllUser(): Flowable<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Insert
    fun insertAll(users: ArrayList<User>)
}