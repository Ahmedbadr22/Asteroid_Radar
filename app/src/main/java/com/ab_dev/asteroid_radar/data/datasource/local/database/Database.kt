package com.ab_dev.asteroid_radar.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ab_dev.asteroid_radar.data.datasource.local.dao.AsteroidDao
import com.ab_dev.asteroid_radar.data.model.DatabaseAsteroid

@Database(entities = [DatabaseAsteroid::class], version = 1, exportSchema = false)
abstract class AsteroidsDatabase : RoomDatabase() {
    abstract val asteroidDao : AsteroidDao
}

object Database {
    private lateinit var INSTANCE : AsteroidsDatabase

    fun getDatabaseInstance(context: Context) : AsteroidsDatabase {
        synchronized(AsteroidsDatabase::class.java) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AsteroidsDatabase::class.java,
                    "asteroids"
                ).build()
            }
        }

        return INSTANCE
    }
}