package com.manimal.marvelcharacters

import android.app.Application
import com.manimal.di.module.repositoryModule
import com.manimal.di.module.serviceModule
import com.manimal.di.module.useCaseModule
import com.manimal.marvelcharacters.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class MarvelCharactersApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MarvelCharactersApp)
            modules(
                listOf(
                    viewModelModule,
                    useCaseModule,
                    repositoryModule,
                    serviceModule
                )
            )
        }
    }
}
