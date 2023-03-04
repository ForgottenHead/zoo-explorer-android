package com.mendelu.xstast12.zooexplorer.ui.activities

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.*

import com.mendelu.xstast12.zooexplorer.navigation.Destination
import com.mendelu.xstast12.zooexplorer.navigation.NavGraph
import com.mendelu.xstast12.zooexplorer.ui.elements.CustomDialogLocation
import com.mendelu.xstast12.zooexplorer.ui.theme.ZooExplorerTheme

class MainActivity : ComponentActivity() {

    companion object {
        /**
         * Creates intent for the activity.
         * @param context the context to run the intent
         */
        fun createIntent(context: AppCompatActivity): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZooExplorerTheme {
                //RequestPermission(permission = ACCESS_FINE_LOCATION)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(startDestination = Destination.MapScreen.route)
                }
            }
        }
    }
}

