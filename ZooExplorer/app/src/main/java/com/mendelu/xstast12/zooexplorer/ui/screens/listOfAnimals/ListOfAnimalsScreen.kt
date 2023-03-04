package com.mendelu.xstast12.zooexplorer.ui.screens.listOfAnimals

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.models.Animal
import com.mendelu.xstast12.zooexplorer.models.ScreenState
import com.mendelu.xstast12.zooexplorer.navigation.INavigationRouter
import com.mendelu.xstast12.zooexplorer.ui.elements.*
import com.mendelu.xstast12.zooexplorer.ui.theme.*
import org.koin.androidx.compose.getViewModel

val tag: String = "ListOfAnimalsScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfAnimalsScreen(
    navigation: INavigationRouter,
    viewModel: ListOfAnimalsViewModel = getViewModel()
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



    viewModel.animalsUiState.value.let {
        when(it){
            is ListOfAnimalsScreenUiState.Error -> {
                Log.e("$tag errorState", ": How did this happen?")
                screenState.value = ScreenState.Error(it.error)
            }
            is ListOfAnimalsScreenUiState.Start -> viewModel.fetchDatabase()
            is ListOfAnimalsScreenUiState.Success -> {
                screenState.value = ScreenState.DataLoaded(it.data)
            }
        }
    }

    BackArrowScreen(
        topBarText = stringResource(R.string.animal_list),
        bottomBar = { MyNavigationBar(navigation = navigation) },
        content = { paddingValues -> ListOfAnimalsScreenContent(
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding()),
            viewModel = viewModel,
            screenState = screenState.value,
            navigation = navigation) }
    )

}


@Composable
fun ListOfAnimalsScreenContent(
    modifier: Modifier,
    viewModel: ListOfAnimalsViewModel,
    screenState: ScreenState<List<Animal>>,
    navigation: INavigationRouter) {

    screenState.let {
        when(it){
            is ScreenState.DataLoaded -> AnimalsList(
                modifier = modifier,
                viewModel = viewModel,
                navigation = navigation,
                animals = it.data
            )
            is ScreenState.Error -> ErrorScreen(text = it.error.toString())
            is ScreenState.Loading -> LoadingScreen()
        }
    }

}

@Composable
fun AnimalsList(
    modifier: Modifier,
    viewModel: ListOfAnimalsViewModel,
    navigation: INavigationRouter,
    animals: List<Animal>
) {
    Box(modifier = Modifier.background(top_app_bar_light_color)){
        LazyColumn(modifier = modifier.testTag("ListColumn")) {
            animals.forEach {
                item(key = it.id) {
                    AnimalRow(
                        animal = it,
                       // viewModel,
                        onRowClick = {
                            navigation.navigateToDetail(it.id)
                        },
                        onFavClick = { viewModel.changeFavouriteState(it.id, it.isFavourite)},
                        image =  viewModel.getPhoto(it.images[0])
                    )
                }
            }
        }
    }

    
}

