package com.example.loginapp.data.repository

import com.example.loginapp.data.local.dao.UserDao
import com.example.loginapp.data.local.entity.User

/**
 * Repository = penghubung ViewModel dengan sumber data (DAO / Room).
 * ViewModel tidak perlu tahu data berasal dari mana.
 */
class UserRepository(private val dao: UserDao) {

    suspend fun insert(user: User) = dao.insert(user)

    suspend fun login(username: String, password: String): User? =
        dao.login(username, password)

    /** Menyimpan user dummy admin / 12345 bila tabel masih kosong. */
    suspend fun insertDummyUserIfEmpty() {
        if (dao.countUsers() == 0) {
            dao.insert(User(username = "admin", password = "12345"))
        }
    }
}
