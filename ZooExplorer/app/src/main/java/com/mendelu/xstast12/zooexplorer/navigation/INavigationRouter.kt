package com.mendelu.xstast12.zooexplorer.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun getNavController(): NavController
    fun returnBack()
    fun navigateToMap()
    fun navigateToListOfAnimals()
    fun navigateToListOfFavourites()
    fun navigateToDetail(id: Long)
    fun navigateToInfo()

}