package io.github.ldhnam.roomrxjava2.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import io.github.ldhnam.roomrxjava2.data.local.dao.UserDao
import io.github.ldhnam.roomrxjava2.data.model.User


@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}