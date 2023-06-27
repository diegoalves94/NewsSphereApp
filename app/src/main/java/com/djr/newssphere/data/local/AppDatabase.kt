package com.djr.newssphere.data.local

import com.djr.newssphere.data.model.Headline

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Headline::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun headlineDao(): HeadlineDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "headline_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}