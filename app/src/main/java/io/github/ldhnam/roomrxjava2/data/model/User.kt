package io.github.ldhnam.roomrxjava2.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity()
data class User(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var name: String = "",
        var gender: String = "",
        var age: Int = 0,
        var phone: String = "",
        var email: String = "",
        var photo: String = "",
        var created_at: Long = System.currentTimeMillis())