package com.example.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 4)
abstract class MovieListDataBase : RoomDatabase() {
    abstract fun movieDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: MovieListDataBase? = null

        fun createRoomInstance(context: Context): MovieListDataBase {
            if (INSTANCE == null) {
                synchronized(this) { INSTANCE = buidDataBase(context) }
            }
            return INSTANCE!!
        }

        private fun buidDataBase(context: Context): MovieListDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                MovieListDataBase::class.java,
                "movie_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}