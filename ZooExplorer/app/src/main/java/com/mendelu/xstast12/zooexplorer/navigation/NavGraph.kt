package com.mendelu.xstast12.zooexplorer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mendelu.xstast12.zooexplorer.constants.Constants
import com.mendelu.xstast12.zooexplorer.ui.screens.detail.DetailScreen
import com.mendelu.xstast12.zooexplorer.ui.screens.info.InfoScreen
import com.mendelu.xstast12.zooexplorer.ui.screens.listOfAnimals.ListOfAnimalsScreen
import com.mendelu.xstast12.zooexplorer.ui.screens.listOfFavourites.FavouritesScreen
import com.mendelu.xstast12.zooexplorer.ui.screens.map.MapScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    navigation: INavigationRouter = remember { NavigationRouterImpl(navController) },
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = startDestination){

        composable(Destination.MapScreen.route) {
            MapScreen(navigation = navigation)
        }

        composable(Destination.ListOfAnimalsScreen.route){
            ListOfAnimalsScreen(navigation = navigation)
        }
        
        composable(Destination.ListOfFavourites.route){
            FavouritesScreen(navigation = navigation)
        }

        composable(Destination.InfoScreen.route){
            InfoScreen(navigation = navigation)
        }
        
        composable(Destination.DetailScreen.route + "/{id}",
            arguments = listOf(
                navArgument(Constants.ID) {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )){
            val id = it.arguments?.getLong(Constants.ID)
            DetailScreen(navigation = navigation, id = id!!)
        }

    }
}