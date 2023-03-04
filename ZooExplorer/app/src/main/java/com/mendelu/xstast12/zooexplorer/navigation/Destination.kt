package com.mendelu.xstast12.zooexplorer.navigation

sealed class Destination(
    val route: String
){
    object MapScreen: Destination(route = "map_screen")
    object DetailScreen: Destination(route = "detail_screen")
    object ListOfAnimalsScreen: Destination(route = "list_of_animals_screen")
    object ListOfFavourites: Destination(route = "list_of_favourites_screen")
    object InfoScreen: Destination(route = "info_screen")
}
