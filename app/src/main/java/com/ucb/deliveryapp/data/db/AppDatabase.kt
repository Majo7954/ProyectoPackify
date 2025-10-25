package com.ucb.deliveryapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucb.deliveryapp.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
