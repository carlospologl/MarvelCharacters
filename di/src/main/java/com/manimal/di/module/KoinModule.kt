package com.manimal.di.module

import com.manimal.data.remote.CharactersRemoteDataSource
import com.manimal.data.remote.CharactersRemoteDataSourceImpl
import com.manimal.data.repository.CharactersRepositoryImpl
import com.manimal.data.service.CharactersService
import com.manimal.data.service.CharactersServiceImpl
import com.manimal.domain.repository.CharactersRepository
import com.manimal.domain.usecase.CharactersUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { CharactersUseCase(get()) }
}

val repositoryModule = module {
    // Characters
    single<CharactersRepository> { CharactersRepositoryImpl(get()) }
    single<CharactersRemoteDataSource> { CharactersRemoteDataSourceImpl(get()) }
}

val serviceModule = module {
    single<CharactersService> { CharactersServiceImpl() }
}
