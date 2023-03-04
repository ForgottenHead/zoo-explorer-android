package com.mendelu.xstast12.zooexplorer

import android.app.Application
import android.content.Context
import com.mendelu.xstast12.zooexplorer.di.test.databaseModuleTest
import com.mendelu.xstast12.zooexplorer.di.test.internalStorageModuleTest
import com.mendelu.xstast12.zooexplorer.di.test.localRepositoryModuleTest
import com.mendelu.xstast12.zooexplorer.di.test.viewModelModuleTest
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class ZooExplorerApplicationTest: Application() {

        override fun onCreate() {
            super.onCreate()
            appContext = applicationContext
            startKoin {
                androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
                androidContext(this@ZooExplorerApplicationTest)
                modules(listOf(
                    viewModelModuleTest,
                    databaseModuleTest,
                    localRepositoryModuleTest,
                    internalStorageModuleTest
                ))
            }
        }

        companion object {
            lateinit var appContext: Context
                private set
        }

}