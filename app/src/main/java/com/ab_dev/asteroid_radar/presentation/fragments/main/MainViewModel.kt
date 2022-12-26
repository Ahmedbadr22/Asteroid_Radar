package com.ab_dev.asteroid_radar.presentation.fragments.main

import android.app.Application
import androidx.lifecycle.*
import com.ab_dev.asteroid_radar.R
import com.ab_dev.asteroid_radar.data.repository.AsteroidRepository
import com.ab_dev.asteroid_radar.domain.models.Asteroid
import com.ab_dev.asteroid_radar.domain.models.PictureOfDay
import kotlinx.coroutines.launch


class MainViewModel(
    application: Application,
    private val asteroidRepository: AsteroidRepository
) : AndroidViewModel(application) {
    val asteroids : MutableLiveData<List<Asteroid>> = asteroidRepository.asteroidsList as MutableLiveData<List<Asteroid>>
    val imageOfTheDay : LiveData<PictureOfDay> = asteroidRepository.image


    init {
        viewModelScope.launch {
            asteroidRepository.getImageOfTheDay()
            asteroidRepository.refreshAsteroids()
        }
    }

    private fun listAsteroidsWeekList() {
        viewModelScope.launch {
            asteroids.value = asteroidRepository.listAsteroidsWeekList()
        }
    }

    private fun listAsteroidsOfCurrentDateList() {
        viewModelScope.launch {
            asteroids.value = asteroidRepository.listAsteroidsByCurrentDateList()
        }
    }

    fun onFilterOptionChange(itemId: Int) {
        when(itemId) {
            R.id.show_all_menu -> listAsteroidsWeekList()
            R.id.show_rent_menu -> listAsteroidsOfCurrentDateList()
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