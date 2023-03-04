package com.mendelu.xstast12.zooexplorer.ui.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.navigation.INavigationRouter
import com.mendelu.xstast12.zooexplorer.ui.elements.BackArrowScreen
import com.mendelu.xstast12.zooexplorer.ui.theme.top_app_bar_light_color
import org.koin.androidx.compose.getViewModel

@Composable
fun InfoScreen(navigation: INavigationRouter,
                viewModel: InfoScreenViewModel = getViewModel()) {


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

    BackArrowScreen(
        topBarText = stringResource(R.string.infors),
        showBackArrow = true,
        onBackClick = { navigation.returnBack()},
                    content = {InfoScrenContent()}
    )

}

@Composable
fun InfoScrenContent() {

    Column() {

        //photo
        Row(modifier = Modifier.testTag("InfoLogoImage")
            .fillMaxWidth()
            .padding(20.dp)) {
            Image(painter = painterResource(id = R.drawable.zoo_brno_logo),
                contentScale = ContentScale.Fit,
                contentDescription = "zooBrnoLogo")
        }

        //text
        Row(modifier = Modifier.padding(start = 10.dp, top = 20.dp),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
            Column() {
                Text(text = stringResource(R.string.contact),fontSize = 12.sp,  fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 10.dp))
                Text(text = "Zoo Brno a stanice zajmovych cinnosti,", fontSize = 12.sp)
                Text(text = "prispevkova organizace", fontSize = 12.sp)
                Text(text = "U zologicke zahrady 45,", fontSize = 12.sp)
                Text(text = "635 00 Brno", fontSize = 12.sp)

            }
        }


        Row(modifier = Modifier.padding(top = 20.dp)) {
            Column() {
                TextRowSecond(key = stringResource(R.string.info_line), content = "725 840 732")
                TextRowSecond(key = stringResource(R.string.phone), content = "234 234 234")
                TextRowSecond(key = stringResource(R.string.email), content = "zoo@zoobrno.cz")
                TextRowSecond(key = stringResource(R.string.email), content = "Podatelna@zoobrno.cz")
            }
        }


        //Social networks
        Row() {

        }


    }



}

@Composable
fun TextRowSecond(key:String, content: String) {
    Row(modifier = Modifier.padding(start = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
        Text(text = key,fontSize = 12.sp,  fontWeight = FontWeight.Bold,  modifier = Modifier.padding(end = 10.dp))
        Text(text = content, fontSize = 12.sp)
    }

}


