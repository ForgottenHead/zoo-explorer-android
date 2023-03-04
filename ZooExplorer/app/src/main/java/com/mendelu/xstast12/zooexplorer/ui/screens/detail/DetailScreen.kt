package com.mendelu.xstast12.zooexplorer.ui.screens.detail


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.ar.ARActivity
import com.mendelu.xstast12.zooexplorer.models.Animal
import com.mendelu.xstast12.zooexplorer.models.ScreenState
import com.mendelu.xstast12.zooexplorer.navigation.INavigationRouter
import com.mendelu.xstast12.zooexplorer.ui.elements.BackArrowScreen
import com.mendelu.xstast12.zooexplorer.ui.elements.ErrorScreen
import com.mendelu.xstast12.zooexplorer.ui.elements.LoadingScreen
import com.mendelu.xstast12.zooexplorer.ui.theme.md_theme_light_primaryContainer
import com.mendelu.xstast12.zooexplorer.ui.theme.top_app_bar_light_color
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailScreen(navigation: INavigationRouter,
                 viewModel: DetailScreenViewModel = getViewModel(),
                 id: Long,
) {
    viewModel.animalId = id
    val screenState: MutableState<ScreenState<Animal>> = rememberSaveable{
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
            color = top_app_bar_light_color,
        )
    }
    
    
    viewModel.detailScreenUiState.value.let { 
        when(it){
            is DetailScreenUiState.Error -> screenState.value = ScreenState.Error(it.error)
            is DetailScreenUiState.Start -> {
                viewModel.fetchAnimal()
            }
            is DetailScreenUiState.Success -> screenState.value = ScreenState.DataLoaded(it.data)
        }
    }

    DetailScreenScreenContent(viewModel = viewModel,
        navigation = navigation,
        screenState = screenState.value)
    
}

@Composable
fun DetailScreenScreenContent(viewModel: DetailScreenViewModel,
                              navigation: INavigationRouter,
                                screenState: ScreenState<Animal>) {

    when(screenState){
        is ScreenState.DataLoaded ->
            BackArrowScreen(
                topBarText = screenState.data.name,
                content = {
                    DetailScreenContent(
                        viewModel = viewModel,
                        animal = screenState.data,
                        navigation = navigation) },
                onBackClick = { navigation.returnBack() },
                actions = {
                    IconButton(onClick = {
                            viewModel.changeFavouriteState(viewModel.animalId!!,
                            viewModel.getFavoritesState(viewModel.animalId!!)) }) {
                        Icon(painter =
                            if (viewModel.getFavoritesState(viewModel.animalId!!))
                                painterResource(R.drawable.ic_favourite_selected)
                            else painterResource(R.drawable.ic_favourite) ,
                            contentDescription = stringResource(R.string.favourites))
                    }
                },



                showBackArrow = true)

        is ScreenState.Error -> ErrorScreen(text = screenState.error.toString())
        is ScreenState.Loading -> LoadingScreen()
    }
    
}

@Composable
fun DetailScreenContent(viewModel: DetailScreenViewModel,
                        navigation: INavigationRouter,
                        animal: Animal) {

    val context = LocalContext.current
    Column(modifier = Modifier
        .paint(
            painter = painterResource(id = R.drawable.paws),
            contentScale = ContentScale.FillBounds , alpha = 0.1f
        )
        .padding(start = 16.dp)
        .verticalScroll(rememberScrollState())
        ) {

        //Celkove Informacie
        Row(modifier = Modifier.padding(top = 8.dp)) {
            //Info
            Column(modifier = Modifier.weight(0.6f)) {
                MainInfo(header = stringResource(R.string.continent), info = animal.continent)
                MainInfo(header = stringResource(R.string.animal_class), info = animal.animalClass)
                MainInfo(header = stringResource(R.string.order), info = animal.order)
                MainInfo(header = stringResource(R.string.family), info = animal.family)

            }
            //Photo
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(0.4f)) {
                Box(modifier = Modifier
                    .padding(end = 10.dp)
                    .border(
                        BorderStroke(1.dp, color = Color.Black),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .width(150.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(30.dp))
                    ){
                    if (viewModel.animalImage != null){
                        Image(bitmap = viewModel.animalImage!!.asImageBitmap(),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = animal.name)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.padding(20.dp))

        //Zakladne informacie title
        Row() {
            Text(text = stringResource(R.string.base_info), fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.padding(10.dp))

        //jednotlive rows na zakladne informacie
        GeneralInfo(header = stringResource(R.string.length), info = animal.length)
        GeneralInfo(header = stringResource(R.string.weight), info = animal.weight)
        GeneralInfo(header = stringResource(R.string.georange), info = animal.geoRange)
        GeneralInfo(header = stringResource(R.string.diet), info = animal.diet)
        GeneralInfo(header = stringResource(R.string.lifespan), info = animal.lifespan)
        GeneralInfo(header = stringResource(R.string.iucn), info = animal.iucnStatus)


        //Row na button AR
        if (animal.model.isNotEmpty()){
            Row(modifier = Modifier.testTag("ARButton")) {
                Button(onClick = {
                    val arIntent = Intent(context, ARActivity::class.java)
                    arIntent.putExtra("model", animal.model)
                    context.startActivity(arIntent)
                },
                    colors = ButtonDefaults.buttonColors(
                        md_theme_light_primaryContainer),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = stringResource(R.string.show_in_ar), color = Color.Black)
                }
            }
        }



        Row(modifier = Modifier.testTag("IntentToGoogleMaps")) {
            //Row na button Maps
            val gmmIntentUri = Uri.parse("google.navigation:q=${animal.lat},${animal.long}&mode=w")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")

            Button(onClick = {
                mapIntent.resolveActivity(context.packageManager)?.let {
                    context.startActivity(mapIntent)
                }
            },
                colors = ButtonDefaults.buttonColors(
                    md_theme_light_primaryContainer),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text(text = stringResource(R.string.navigate_wih), color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.padding(10.dp))

    }
}

@Composable
fun MainInfo(header: String, info: String) {
    if(info != ""){
        Text(text = header, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif, fontSize = 14.sp)
        Text(info, fontFamily = FontFamily.SansSerif, modifier = Modifier.padding(start = 10.dp, bottom = 8.dp), fontSize = 14.sp)
    }

}

@Composable
fun GeneralInfo(header: String, info: String) {
    if (info != ""){
        Row(modifier = Modifier.padding(bottom = 8.dp)) {
            Column(modifier = Modifier.weight(0.2f)) {
                Text(text = header, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif, fontSize = 14.sp)
            }


            Column(modifier = Modifier.weight(0.8f)) {
                FlowRow() {
                    Text(text = info, fontFamily = FontFamily.SansSerif, fontSize = 14.sp)
                }
            }
        }
        
        Spacer(modifier = Modifier.padding(4.dp))
    }


}
