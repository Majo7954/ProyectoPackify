package com.ucb.deliveryapp.repository

import android.content.Context
import androidx.room.Room
import com.ucb.deliveryapp.data.db.AppDatabase
import com.ucb.deliveryapp.data.entity.User

class UserRepository(context: Context) {

    private val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "delivery_db"
    ).build()

    private val userDao = db.userDao()

    suspend fun registerUser(user: User) = userDao.insertUser(user)
    suspend fun login(email: String, password: String) = userDao.login(email, password)
}
