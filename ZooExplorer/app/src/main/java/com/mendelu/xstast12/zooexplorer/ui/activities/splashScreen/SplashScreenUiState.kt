package com.mendelu.xstast12.zooexplorer.ui.activities.splashScreen

sealed class SplashScreenUiState {
    object Default : SplashScreenUiState()
    object ContinueToApp : SplashScreenUiState()
    class Error(var error: Int): SplashScreenUiState()
}
