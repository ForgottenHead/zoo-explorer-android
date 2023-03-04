package com.mendelu.xstast12.zooexplorer.ui.screens.listOfAnimals

sealed class ListOfAnimalsScreenUiState<out T> {
    class Start : ListOfAnimalsScreenUiState<Nothing>()
    class Success<T>(var data: T) : ListOfAnimalsScreenUiState<T>()
    class Error(var error: Int) : ListOfAnimalsScreenUiState<Nothing>()
}
