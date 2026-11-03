package com.example.loginapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Tabel "users" di Room Database.
 * Username dibuat unik agar user dummy tidak tersimpan dua kali.
 */
@Entity(
    tableName = "users",
    indices = [Index(value = ["username"], unique = true)]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String // plaintext hanya untuk pembelajaran; aplikasi nyata wajib hashing
)
