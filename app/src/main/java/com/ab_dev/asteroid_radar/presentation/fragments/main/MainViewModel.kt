package com.ab_dev.asteroid_radar.presentation.fragments.main

import android.app.Application
import androidx.lifecycle.*
import com.ab_dev.asteroid_radar.data.repository.AsteroidRepository
import com.ab_dev.asteroid_radar.domain.models.Asteroid
import com.ab_dev.asteroid_radar.domain.models.PictureOfDay
import kotlinx.coroutines.launch


class MainViewModel(
    application: Application,
    private val asteroidRepository: AsteroidRepository
) : AndroidViewModel(application) {
    val asteroids : LiveData<List<Asteroid>> = asteroidRepository.asteroidsList
    val imageOfTheDay : LiveData<PictureOfDay> = asteroidRepository.image


    init {
        viewModelScope.launch {
            asteroidRepository.getImageOfTheDay()
            asteroidRepository.refreshAsteroids()
        }
    }


    // view Model Factory
    class Factory(
        private val application: Application,
        private val asteroidRepository: AsteroidRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(application, asteroidRepository) as T
            }
            throw IllegalArgumentException("Unable to construct view-model")
        }
    }
}