package com.mendelu.xstast12.zooexplorer.ui.elements

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.map.MarkerUtil
import com.mendelu.xstast12.zooexplorer.models.Animal
import com.mendelu.xstast12.zooexplorer.ui.screens.listOfAnimals.ListOfAnimalsViewModel
import com.mendelu.xstast12.zooexplorer.ui.theme.card_list_favorites_color
import com.mendelu.xstast12.zooexplorer.ui.theme.card_list_ligth_color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimalRow(animal: Animal,
              onRowClick: () -> Unit = {},
              onFavClick: () -> Unit = {},
              image: Bitmap?) {
    Surface(shape = RoundedCornerShape(20),
        shadowElevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(70.dp),
        color = card_list_ligth_color,
        onClick = onRowClick

    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {

            Column(
                modifier = Modifier
                    .weight(0.10f)
                    .wrapContentSize(),
                horizontalAlignment = Alignment.Start) {
                //Icon
                when(animal.animalClass){
                    "Savci (Mammalia)" -> {
                        Icon(painter = painterResource(id = R.drawable.mammal_round_icon),
                            contentDescription = "mammal_icon",
                            tint = Color.Unspecified)
                    }
                    "Plazi (Reptilia)" -> {
                        Icon(painter = painterResource(id = R.drawable.reptile_round_icon),
                            contentDescription = "reptile_icon",
                            tint = Color.Unspecified)
                    }
                    else -> {
                        Icon(painter = painterResource(id = R.drawable.fish_round_icon),
                            contentDescription = "fish_icon",
                            tint = Color.Unspecified)
                    }
                }
            }

            Column(modifier = Modifier
                .weight(0.50f)
                .padding(start = 10.dp)) {
                //Text
                Text(text = animal.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.labelLarge)

                animal.exposition?.let {
                    Spacer(modifier = Modifier.padding(end = 10.dp))
                    Text(text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }



            Column(modifier = Modifier.weight(0.15f)) {
                IconButton(onClick = onFavClick ) {
                    Icon(painter = if (animal.isFavourite) painterResource(R.drawable.ic_favourite_selected)
                    else painterResource(R.drawable.ic_favourite) ,
                        contentDescription = "Favourite")
                }


//                if (animal.isFavourite){
//                    IconButton(onClick = onFavClick ) {
//                        Icon(painter = painterResource(R.drawable.ic_favourite_selected) ,
//                            contentDescription = "Favourite")
//                    }
//                }else{
//                    IconButton(onClick = onFavClick ) {
//                        Icon(painter = painterResource(R.drawable.ic_favourite) ,
//                            contentDescription = "Not Favourite")
//                    }
//                }
            }

            Column(modifier = Modifier.weight(0.25f),  horizontalAlignment = Alignment.End) {
                if (image != null){

                    Box(modifier = Modifier
                        .padding(10.dp)
                        .border(BorderStroke(1.dp, color = Color.Black), shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .width(100.dp)
                        .height(100.dp)

                    ) {
                        Image(bitmap = image.asImageBitmap(),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = animal.name)
                    }

                }else{
                    Box(modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .width(100.dp)
                        .height(100.dp)
                        .background(Color.Black)
                    ) {
                        Text(text = "PLACEHOLDER", color = Color.White, fontSize = 10.sp)
                    }
                }

            }
        }
    }


}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteAnimalRow(animal: Animal,
              onRowClick: () -> Unit = {},
              onFavClick: () -> Unit = {},
              image: Bitmap?) {
    Surface(shape = RoundedCornerShape(20),
        shadowElevation = 10.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(70.dp),
        color = card_list_favorites_color,
        onClick = onRowClick

    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {

            Column(
                modifier = Modifier
                    .weight(0.10f)
                    .wrapContentSize(),
                horizontalAlignment = Alignment.Start) {
                //Icon
                when(animal.animalClass){
                    "Savci (Mammalia)" -> {
                        Icon(painter = painterResource(id = R.drawable.mammal_round_icon),
                            contentDescription = "mammal_icon",
                            tint = Color.Unspecified)
                    }
                    "Plazi (Reptilia)" -> {
                        Icon(painter = painterResource(id = R.drawable.reptile_round_icon),
                            contentDescription = "reptile_icon",
                            tint = Color.Unspecified)
                    }
                    else -> {
                        Icon(painter = painterResource(id = R.drawable.fish_round_icon),
                            contentDescription = "fish_icon",
                            tint = Color.Unspecified)
                    }
                }
            }

            Column(modifier = Modifier
                .weight(0.50f)
                .padding(start = 10.dp)) {
                //Text
                Text(text = animal.name,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.labelLarge)

                animal.exposition?.let {
                    Spacer(modifier = Modifier.padding(end = 10.dp))
                    Text(text = it,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            Column(modifier = Modifier.weight(0.15f)) {
                IconButton(onClick = onFavClick ) {
                    Icon(painter = if (animal.isFavourite) painterResource(R.drawable.ic_favourite_selected)
                    else painterResource(R.drawable.ic_favourite) ,
                        contentDescription = "Favourite",
                        tint = Color.White)
                }
            }

            Column(modifier = Modifier.weight(0.25f),  horizontalAlignment = Alignment.End) {
                if (image != null){

                    Box(modifier = Modifier
                        .padding(10.dp)
                        .border(BorderStroke(1.dp, color = Color.White), shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .width(100.dp)
                        .height(100.dp)


                    ) {
                        Image(bitmap = image.asImageBitmap(),
                            contentScale = ContentScale.FillBounds,
                            contentDescription = animal.name)
                    }

                }else{
                    Box(modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .width(100.dp)
                        .height(100.dp)
                        .background(Color.Black)
                    ) {
                        Text(text = "PLACEHOLDER", color = Color.White, fontSize = 10.sp)
                    }
                }

            }
        }
    }


}