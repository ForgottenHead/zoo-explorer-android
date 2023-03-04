package com.mendelu.xstast12.zooexplorer.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.ui.theme.bottom_bar_light_color
import com.mendelu.xstast12.zooexplorer.ui.theme.top_app_bar_light_color

@Composable
fun BackArrowScreen(
    topBarText: String,
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    onBackClick: () -> Unit = {},
    showBackArrow: Boolean = false) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement =  Arrangement.Start

                    ) {
                        Text(
                            text = topBarText,
                            style = MaterialTheme.typography.headlineLarge,
                            fontFamily = FontFamily.SansSerif,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 24.sp
                        )
                    }


                },
                actions = actions,
                navigationIcon = {
                    if (showBackArrow){
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back_arrow),
                                tint = Color.Black
                            )
                        }
                    }
                     },
                elevation = 0.dp,
                backgroundColor = top_app_bar_light_color
            )
        },
        bottomBar = bottomBar,
        content = content
    )
}

@Composable
@Preview
fun textPreviw() {
    Column() {

        Text(
            text = "Animal List",
            style = MaterialTheme.typography.headlineLarge,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 26.sp,


            )
    }

}


