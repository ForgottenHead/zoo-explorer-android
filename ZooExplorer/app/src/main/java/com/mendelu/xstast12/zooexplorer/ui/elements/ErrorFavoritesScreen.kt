package com.mendelu.xstast12.zooexplorer.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mendelu.xstast12.zooexplorer.R

@Composable
fun ErrorFavoritesScreen() {
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Column(modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Box(modifier = Modifier.width(200.dp)){
                Image(
                    painter = painterResource(R.drawable.no_favorites),
                    contentDescription = null,
                    contentScale = ContentScale.Fit ,
                    modifier = Modifier.testTag("NoAnimalsImage")
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                )
            }



            Text(
                text = "Its empty here.",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "Add some animals.",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )

        }
    }
}

@Composable
@Preview
fun NoFavPreview() {
    ErrorFavoritesScreen()
}