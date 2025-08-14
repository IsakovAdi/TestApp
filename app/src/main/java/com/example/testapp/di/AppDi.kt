package com.example.testapp.di

import com.example.testapp.fragments.favorites.FavoritesFragmentViewModel
import com.example.testapp.fragments.home.HomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<HomeFragmentViewModel> {
        HomeFragmentViewModel(
            courseUseCase = get(),
            storageCoursesUseCase = get()
        )
    }

    viewModel<FavoritesFragmentViewModel> {
        FavoritesFragmentViewModel(
            storageCoursesUseCase = get(),
        )
    }
}

