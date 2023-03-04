package com.mendelu.xstast12.zooexplorer.ui.screens.detail

sealed class DetailScreenUiState<out T>{
    class Start : DetailScreenUiState<Nothing>()
    class Success<T>(var data: T) : DetailScreenUiState<T>()
    class Error(var error: Int) : DetailScreenUiState<Nothing>()
}
