package com.mendelu.xstast12.zooexplorer.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.navigation.Destination
import com.mendelu.xstast12.zooexplorer.navigation.INavigationRouter
import com.mendelu.xstast12.zooexplorer.ui.theme.*

@Composable
fun MyNavigationBar(navigation: INavigationRouter) {
    val currentDestination: String? =
        navigation.getNavController()
        .currentBackStackEntry?.destination
        ?.route

    NavigationBar(
        containerColor = bottom_bar_light_color,

    ) {
        NavigationBarItem(
            icon = {
                Column(verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally)    {
                    if (isDestinationMap(currentDestination)){
                        Icon(painter = painterResource(R.drawable.ic_map_selected) ,
                            contentDescription = "Icon Map Selected")
                    }else{
                        Icon(painter = painterResource(R.drawable.ic_map) ,
                            contentDescription = "Icon Map")
                    }
                    Text(text = stringResource(R.string.map))
                }

                 },
            //label = { Text("Map") },
            selected = isDestinationMap(currentDestination),
            onClick = { navigation.navigateToMap()},
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = md_theme_light_inversePrimary)

        )

        NavigationBarItem(
            icon = {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    if (isDestinationList(currentDestination)) {
                        Icon(
                            painter = painterResource(R.drawable.ic_list_selected),
                            contentDescription = "Icon List Selected"
                        )
                    } else {
                        Icon(
                            painter = painterResource(R.drawable.ic_list),
                            contentDescription = "Icon List"
                        )
                    }
                    Text(text = stringResource(R.string.list))
                }
            },
            //label = { Text("List") },
            selected = isDestinationList(currentDestination),
            onClick = { navigation.navigateToListOfAnimals() },
            colors = NavigationBarItemDefaults.colors(
                indicatorColor = md_theme_light_inversePrimary)
        )


        NavigationBarItem(
            icon = {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)    {
                    if (isDestinationFavourites(currentDestination)){
                        Icon(painter = painterResource(R.drawable.ic_favourite_selected) ,
                            contentDescription = "Icon List Selected")
                    }else{
                        Icon(painter = painterResource(R.drawable.ic_favourite) ,
                            contentDescription = "Icon List")
                    }
                    Text(text = stringResource(R.string.favourites))
                }
            },
            selected = isDestinationFavourites(currentDestination),
            onClick = { navigation.navigateToListOfFavourites() },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Red,
                indicatorColor = md_theme_light_inversePrimary)
        )


    }
}

fun isDestinationMap(currentDestination: String?): Boolean{
    return currentDestination == Destination.MapScreen.route
}

fun isDestinationList(currentDestination: String?): Boolean{
    return currentDestination == Destination.ListOfAnimalsScreen.route
}

fun isDestinationFavourites(currentDestination: String?):Boolean{
    return currentDestination == Destination.ListOfFavourites.route
}