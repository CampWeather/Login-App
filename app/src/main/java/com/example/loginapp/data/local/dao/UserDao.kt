package com.example.loginapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.loginapp.data.local.entity.User

@Dao
interface UserDao {

    /** IGNORE: jika username sudah ada, insert dilewati (tidak error). */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    /** Mengembalikan user jika username & password cocok, null jika tidak. */
    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): User?

    @Query("SELECT COUNT(*) FROM users")
    suspend fun countUsers(): Int
}
