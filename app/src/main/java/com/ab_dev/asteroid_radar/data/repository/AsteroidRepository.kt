package com.ab_dev.asteroid_radar.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ab_dev.asteroid_radar.app.getFormattedCurrentDate
import com.ab_dev.asteroid_radar.app.toDomain
import com.ab_dev.asteroid_radar.app.toDomainList
import com.ab_dev.asteroid_radar.data.datasource.local.database.AsteroidsDatabase
import com.ab_dev.asteroid_radar.data.network.api.MoshiNetwork
import com.ab_dev.asteroid_radar.data.network.api.Network
import com.ab_dev.asteroid_radar.data.network.api.parseAsteroidsJsonResult
import com.ab_dev.asteroid_radar.domain.models.Asteroid
import com.ab_dev.asteroid_radar.domain.models.PictureOfDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidRepository(private val database: AsteroidsDatabase) {
    val image = MutableLiveData<PictureOfDay>()
    var asteroidsList: LiveData<List<Asteroid>> =
        Transformations.map(database.asteroidDao.getAsteroidsByDate(getFormattedCurrentDate())) { databaseAsteroidsList ->
            databaseAsteroidsList.toDomain()
        }

    suspend fun refreshAsteroids() {
        withContext(Dispatchers.IO) {
            val currentDate = getFormattedCurrentDate()
            val stringJsonResponse = Network.asteroidApi.getAsteroids(currentDate)
            val jsonObject = JSONObject(stringJsonResponse)
            val asteroidsList: List<Asteroid> = parseAsteroidsJsonResult(jsonObject)
            database.asteroidDao.insertAll(asteroidsList.toDomainList())
        }
    }

    suspend fun listAsteroidsWeekList(): List<Asteroid> = withContext(Dispatchers.IO) {
        database.asteroidDao.getAsteroidsOfWeek().toDomain()
    }

    suspend fun listAsteroidsByCurrentDateList(): List<Asteroid> = withContext(Dispatchers.IO) {
        val currentDate = getFormattedCurrentDate()
        database.asteroidDao.getAsteroidsByDateList(currentDate).toDomain()
    }

    suspend fun getImageOfTheDay() =
        withContext(Dispatchers.IO) {
            val response = MoshiNetwork.asteroidApi.getImageOfTheDay()
            withContext(Dispatchers.Main) {
                image.value = response
            }
        }
}