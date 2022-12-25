package com.ab_dev.asteroid_radar.data.datasource.remote

import com.ab_dev.asteroid_radar.app.Constants
import com.ab_dev.asteroid_radar.domain.models.PictureOfDay
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidDatasource {
    @GET(Constants.LIST_ALL_ASTEROIDS)
    suspend fun getAsteroids(@Query(value = "start_date") startDate : String) : String

    @GET(Constants.IMAGE_OF_DAY_URL)
    suspend fun getImageOfTheDay() : PictureOfDay
}