package com.mendelu.xstast12.zooexplorer

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mendelu.xstast12.zooexplorer.navigation.INavigationRouter
import com.mendelu.xstast12.zooexplorer.navigation.NavigationRouterImpl
import com.mendelu.xstast12.zooexplorer.ui.activities.MainActivity
import com.mendelu.xstast12.zooexplorer.ui.activities.splashScreen.SplashScreenActivity
import com.mendelu.xstast12.zooexplorer.ui.elements.ErrorFavoritesScreen
import com.mendelu.xstast12.zooexplorer.ui.screens.detail.DetailScreen
import com.mendelu.xstast12.zooexplorer.ui.screens.info.InfoScreen
import com.mendelu.xstast12.zooexplorer.ui.screens.listOfAnimals.ListOfAnimalsScreen
import com.mendelu.xstast12.zooexplorer.ui.screens.listOfFavourites.FavouritesList
import com.mendelu.xstast12.zooexplorer.ui.screens.listOfFavourites.FavouritesScreen
import com.mendelu.xstast12.zooexplorer.ui.screens.map.MapScreen
import com.mendelu.xstast12.zooexplorer.ui.theme.ZooExplorerTheme
import okhttp3.internal.wait

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest{

    @get:Rule()
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.mendelu.xstast12.zooexplorer", appContext.packageName)
    }

    @Test
    fun mapPresented(){
        composeRule.activity.setContent {
            ZooExplorerTheme() {
                MapScreen(navigation = NavigationRouterImpl(rememberNavController()))
            }
        }
        with(composeRule){
            waitForIdle()
            onNodeWithTag("MapTag").assertExists(null)
            onNodeWithTag("infoButton").assertExists(null)
            //onNodeWithTag("InfoLogoImage").assertExists(null)
        }
    }

    @Test
    fun infoPresented(){
        composeRule.activity.setContent {
            ZooExplorerTheme() {
                InfoScreen(navigation = NavigationRouterImpl(rememberNavController()))
            }
        }
        with(composeRule){
            waitForIdle()
            onNodeWithTag("InfoLogoImage").assertExists(null)
        }
    }


    @Test
    fun ListPageIsDisplayed(){
        composeRule.activity.setContent {
            ZooExplorerTheme() {
                ListOfAnimalsScreen(navigation = NavigationRouterImpl(rememberNavController()))
            }
        }
        with(composeRule){
            waitForIdle()
            onNodeWithTag("ListColumn").assertExists(null)
        }
    }

    @Test
    fun ErrorPage(){
        composeRule.activity.setContent {
            ZooExplorerTheme() {
                ErrorFavoritesScreen()
            }
        }
        with(composeRule){
            waitForIdle()
            onNodeWithTag("NoAnimalsImage").assertExists(null)
        }
    }

//
//    @Test
//    fun detailpageGoogleNavigationPresented(){
//        composeRule.activity.setContent {
//            ZooExplorerTheme() {
//                DetailScreen(navigation = NavigationRouterImpl(rememberNavController()), id = 0)
//            }
//        }
//        with(composeRule){
//
//            //waitForIdle()
//            onNodeWithTag("IntentToGoogleMaps").assertExists(null)
//        }
//    }
//
//    @Test
//    fun detailpageARIsDisplayed(){
//        composeRule.activity.setContent {
//            ZooExplorerTheme() {
//                DetailScreen(navigation = NavigationRouterImpl(rememberNavController()), id = 0)
//            }
//        }
//        with(composeRule){
//            waitForIdle()
//            onNodeWithTag("ARButton").assertExists(null)
//        }
//    }





}

