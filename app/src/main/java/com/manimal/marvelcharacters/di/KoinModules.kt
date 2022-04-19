package com.manimal.marvelcharacters.di

import com.manimal.marvelcharacters.features.characters.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CharactersViewModel(get()) }
}
