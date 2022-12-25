package com.ab_dev.asteroid_radar.app.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ab_dev.asteroid_radar.data.datasource.local.database.Database
import com.ab_dev.asteroid_radar.data.repository.AsteroidRepository


class RefreshDataWork(context: Context, params: WorkerParameters) : CoroutineWorker(context, params = params) {
    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = Database.getDatabaseInstance(context = applicationContext)
        val repository = AsteroidRepository(database)

        return try {
            repository.refreshAsteroids()
            Result.success()
        } catch (exception: Exception) {
            Result.retry()
        }
    }

}