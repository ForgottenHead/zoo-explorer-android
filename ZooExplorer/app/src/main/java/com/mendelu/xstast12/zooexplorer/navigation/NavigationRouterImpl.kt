package com.mendelu.xstast12.zooexplorer.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(private val navController: NavController)
    : INavigationRouter {
    override fun getNavController(): NavController {
        return navController
    }

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToMap() {
        navController.navigate(Destination.MapScreen.route)
    }

    override fun navigateToListOfAnimals() {
        navController.navigate(Destination.ListOfAnimalsScreen.route)
    }

    override fun navigateToListOfFavourites() {
        navController.navigate(Destination.ListOfFavourites.route)
    }

    override fun navigateToDetail(id: Long) {
        navController.navigate(Destination.DetailScreen.route + "/" + id)
    }

    override fun navigateToInfo() {
        navController.navigate(Destination.InfoScreen.route)
    }
}