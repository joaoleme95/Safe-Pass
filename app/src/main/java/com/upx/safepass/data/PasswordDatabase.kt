package com.upx.safepass.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PasswordEntity::class], version = 1)
abstract class PasswordDatabase : RoomDatabase() {
    abstract fun passwordDao(): PasswordDao

    companion object {
        fun getInstance(context: Context): PasswordDatabase =
            Room.databaseBuilder(
                context,
                PasswordDatabase::class.java,
                "password_db"
            ).build()
    }
}