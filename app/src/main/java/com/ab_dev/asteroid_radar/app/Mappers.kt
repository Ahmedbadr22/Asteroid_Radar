package com.ab_dev.asteroid_radar.app

import com.ab_dev.asteroid_radar.data.model.DatabaseAsteroid
import com.ab_dev.asteroid_radar.domain.models.Asteroid

fun List<Asteroid>.toDomainList() : List<DatabaseAsteroid> {
    return map { asteroid ->
        DatabaseAsteroid(
            asteroid.id,
            asteroid.codename,
            asteroid.closeApproachDate,
            asteroid.absoluteMagnitude,
            asteroid.estimatedDiameter,
            asteroid.relativeVelocity,
            asteroid.distanceFromEarth,
            asteroid.isPotentiallyHazardous
        )
    }
}

fun List<DatabaseAsteroid>.toDomain() : List<Asteroid> {
    return map { asteroid ->
        Asteroid(
            asteroid.id,
            asteroid.codename,
            asteroid.closeApproachDate,
            asteroid.absoluteMagnitude,
            asteroid.estimatedDiameter,
            asteroid.relativeVelocity,
            asteroid.distanceFromEarth,
            asteroid.isPotentiallyHazardous
        )
    }
}