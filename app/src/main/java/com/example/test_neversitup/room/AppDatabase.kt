package com.example.test_neversitup.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LoginModel::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    companion object {
        fun getAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "db_app").build()
    }

    abstract fun customerDao(): CustomerDao

}