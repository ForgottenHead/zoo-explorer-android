package com.mendelu.xstast12.zooexplorer.ui.screens.listOfFavourites

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.models.Animal
import com.mendelu.xstast12.zooexplorer.models.ScreenState
import com.mendelu.xstast12.zooexplorer.navigation.INavigationRouter
import com.mendelu.xstast12.zooexplorer.ui.elements.*
import com.mendelu.xstast12.zooexplorer.ui.theme.md_theme_dark_tertiary
import com.mendelu.xstast12.zooexplorer.ui.theme.md_theme_light_tertiaryContainer
import com.mendelu.xstast12.zooexplorer.ui.theme.top_app_bar_light_color
import org.koin.androidx.compose.getViewModel

val tag: String = "ListFavouritesScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(navigation: INavigationRouter,
                     viewModel: FavouritesScreenViewModel = getViewModel()
) {

    val screenState: MutableState<ScreenState<List<Animal>>> = rememberSaveable{
        mutableStateOf(ScreenState.Loading())
    }

    //MARK: SYSTEM UI CONTROLLER COLORS
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    //TODO: Set systembar to transparent in map
    SideEffect {
        systemUiController.setStatusBarColor(
            //color = Color.Transparent,
            color = top_app_bar_light_color,
            darkIcons = useDarkIcons
        )
        //TODO: CHANGE FOR SUPPORT OF DARK MODE
        systemUiController.setNavigationBarColor(
            color = md_theme_light_tertiaryContainer,
        )
    }

    viewModel.favouriteAnimalsUiState.value.let {
        when(it){
            is FavouritesScreenUiState.Error -> {
                Log.e("$tag errorState", it.error.toString())
                screenState.value = ScreenState.Error(it.error)
            }
            is FavouritesScreenUiState.Start -> viewModel.fetchDatabse()
            is FavouritesScreenUiState.Success -> {
                screenState.value = ScreenState.DataLoaded(it.data)
            }
        }
    }

    BackArrowScreen(
        topBarText = stringResource(R.string.favourite_animals_list),
        bottomBar = { MyNavigationBar(navigation = navigation) },
        content = { paddingValues -> FavouritesScreenContent(
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding()),
            viewModel = viewModel,
            screenState = screenState.value,
            navigation = navigation) }
    )
}

@Composable
fun FavouritesScreenContent(
    modifier: Modifier,
    viewModel: FavouritesScreenViewModel,
    screenState: ScreenState<List<Animal>>,
    navigation: INavigationRouter) {

    screenState.let {
        when(it){
            is ScreenState.DataLoaded -> FavouritesList(
                modifier = modifier,
                viewModel = viewModel,
                navigation = navigation,
                animals = it.data
            )
            is ScreenState.Error -> ErrorFavoritesScreen()
            is ScreenState.Loading -> LoadingScreen()
        }
    }
}

@Composable
fun FavouritesList(
    modifier: Modifier,
    viewModel: FavouritesScreenViewModel,
    navigation: INavigationRouter,
    animals: List<Animal>
) {
    Box(modifier = Modifier.fillMaxSize().background(top_app_bar_light_color)) {
        LazyColumn(modifier = modifier) {
            animals.forEach {
                item(key = it.id) {
                    FavoriteAnimalRow(
                        animal = it,
                        image = viewModel.getPhoto(it.images[0]),
                        onFavClick = { viewModel.changeFavouriteState(it.id, !it.isFavourite) },
                        onRowClick = { navigation.navigateToDetail(it.id) }
                    )

                }
            }
        }
    }
}

@Composable
fun FavouriteRow(animal: Animal, onRowClick: () -> Unit, onFavClick: () -> Unit) {
    Surface(shape = RoundedCornerShape(20),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(70.dp),
        color = md_theme_dark_tertiary
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 16.dp)) {
            Text(text = animal.id.toString())
            Spacer(modifier = Modifier.padding(end = 10.dp))
            Text(text = animal.name)
            Spacer(modifier = Modifier.padding(end = 10.dp))


            if (animal.isFavourite){
                IconButton(onClick = onFavClick ) {
                    Icon(painter = painterResource(R.drawable.ic_favourite_selected) ,
                        contentDescription = "Favourite")
                }
            }else{
                IconButton(onClick = onFavClick ) {
                    Icon(painter = painterResource(R.drawable.ic_favourite) ,
                        contentDescription = "Not Favourite")
                }
            }

        }
    }
}