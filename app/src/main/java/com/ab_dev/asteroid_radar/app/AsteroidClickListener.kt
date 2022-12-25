package com.ab_dev.asteroid_radar.app

import com.ab_dev.asteroid_radar.domain.models.Asteroid

class AsteroidClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = clickListener(asteroid)
}