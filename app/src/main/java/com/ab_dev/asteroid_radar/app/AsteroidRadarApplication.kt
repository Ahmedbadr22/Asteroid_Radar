package com.ab_dev.asteroid_radar.app

import android.app.Application
import androidx.work.*
import com.ab_dev.asteroid_radar.app.work.RefreshDataWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AsteroidRadarApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initWorkManager()
    }

    private fun initWorkManager() {
        CoroutineScope(Dispatchers.Default).launch {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .apply {
                    setRequiresDeviceIdle(true)
                }.build()


            val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWork>(1, TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()
            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                RefreshDataWork.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest
            )
        }
    }
}