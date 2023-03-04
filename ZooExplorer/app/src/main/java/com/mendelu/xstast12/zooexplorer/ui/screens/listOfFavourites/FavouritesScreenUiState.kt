package com.mendelu.xstast12.zooexplorer.ui.screens.listOfFavourites


sealed class FavouritesScreenUiState<out T>{
    class Start : FavouritesScreenUiState<Nothing>()
    class Success<T>(var data: T) : FavouritesScreenUiState<T>()
    class Error(var error: Int) : FavouritesScreenUiState<Nothing>()
}
