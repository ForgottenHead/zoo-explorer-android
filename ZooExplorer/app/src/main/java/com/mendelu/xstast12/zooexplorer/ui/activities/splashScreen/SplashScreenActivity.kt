package com.mendelu.xstast12.zooexplorer.ui.activities.splashScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.mendelu.xstast12.zooexplorer.navigation.Destination
import com.mendelu.xstast12.zooexplorer.navigation.NavGraph
import com.mendelu.xstast12.zooexplorer.ui.activities.MainActivity
import com.mendelu.xstast12.zooexplorer.ui.elements.LoadingScreen
import com.mendelu.xstast12.zooexplorer.ui.theme.ZooExplorerTheme
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.FileOutputStream


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashScreenActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        //val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        //splashScreen.setKeepOnScreenCondition { true }


        lifecycleScope.launchWhenResumed {
            viewModel.splashScreenState.collect{ it ->
                when(it){

                    //TODO add state to check internet connection https://www.youtube.com/watch?v=sBMo_bregoY

                    SplashScreenUiState.Default -> {
                        viewModel.checkAppState()
                        setContent{
                            ZooExplorerTheme {
                                Surface(
                                    modifier = Modifier.fillMaxSize(),
                                    color = MaterialTheme.colorScheme.background
                                ) {
                                    LoadingScreen(text = "Please wait until we download necessary data.")
                                }
                            }
                        }
                    }

                    is SplashScreenUiState.Error -> {
                        Log.d("SplashscreenActivity", "Error")
                    }

                    SplashScreenUiState.ContinueToApp -> {
                            continueToApp()
                    }
                }
            }
        }


    }


    private fun continueToApp(){
        startActivity(MainActivity.createIntent(this))
        finish()
    }
}
