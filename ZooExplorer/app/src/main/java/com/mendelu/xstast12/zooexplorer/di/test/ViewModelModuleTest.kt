package com.mendelu.xstast12.zooexplorer.di.test

import com.mendelu.xstast12.zooexplorer.ui.activities.splashScreen.SplashScreenActivityViewModel
import com.mendelu.xstast12.zooexplorer.ui.screens.detail.DetailScreenViewModel
import com.mendelu.xstast12.zooexplorer.ui.screens.info.InfoScreenViewModel
import com.mendelu.xstast12.zooexplorer.ui.screens.listOfAnimals.ListOfAnimalsViewModel
import com.mendelu.xstast12.zooexplorer.ui.screens.listOfFavourites.FavouritesScreenViewModel
import com.mendelu.xstast12.zooexplorer.ui.screens.map.MapScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModuleTest = module {

    viewModel {
        SplashScreenActivityViewModel(
            get(),
            get(),
            get(),
            get()
        )
    }

    viewModel {
        MapScreenViewModel(get(), get())
    }

    viewModel {
        ListOfAnimalsViewModel(get(),get())
    }

    viewModel {
        FavouritesScreenViewModel(get(), get())
    }

    viewModel {
        DetailScreenViewModel(get(), get())
    }

    viewModel {
        InfoScreenViewModel()
    }
}