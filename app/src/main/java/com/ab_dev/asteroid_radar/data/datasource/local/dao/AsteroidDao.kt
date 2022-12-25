package com.ab_dev.asteroid_radar.data.datasource.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ab_dev.asteroid_radar.data.model.DatabaseAsteroid

@Dao
interface AsteroidDao {
    @Query("SELECT * FROM databaseasteroid WHERE closeApproachDate = :date ORDER BY closeApproachDate ASC")
    fun getAsteroidsList(date: String): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(databaseAsteroidsList: List<DatabaseAsteroid>)
}