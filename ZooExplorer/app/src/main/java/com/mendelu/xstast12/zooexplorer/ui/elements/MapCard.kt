package com.mendelu.xstast12.zooexplorer.ui.elements

import android.graphics.Bitmap
import android.widget.Space
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.Marker
import com.mendelu.xstast12.zooexplorer.ui.theme.card_map_light_color
import com.mendelu.xstast12.zooexplorer.ui.theme.md_theme_light_primary
import com.mendelu.xstast12.zooexplorer.ui.theme.md_theme_light_primaryContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapCard(marker: Marker, onClick: () -> Unit, image: Bitmap?) {
    Card(modifier = Modifier
        .padding(bottom = 40.dp)
        .fillMaxWidth(0.6f)
        .height(80.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.outlinedCardColors(card_map_light_color),
        border = BorderStroke(0.5.dp, Color.Black),
        onClick = onClick,
    ) {

        Row() {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(0.6f)) {

                Text(text = marker.title!!,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                    style = MaterialTheme.typography.labelLarge)

                if (marker.snippet!! != "none"){
                    Text(text = marker.snippet!!,
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 14.sp,
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium

                    )
                }

            }

            Spacer(modifier = Modifier.weight(0.1F))

            val modifier = Modifier
                .width(80.dp)
                .height(80.dp)
                .padding(top = 10.dp)

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .weight(0.3f)
                    .padding(end = 10.dp, bottom = 10.dp)) {

                    if (image != null){
                        Box(modifier = modifier
                            .border(BorderStroke(1.dp, color = Color.White), shape = RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Black)
                        ){
                            Image(bitmap = image.asImageBitmap(),
                                contentScale = ContentScale.FillBounds,
                                contentDescription = marker.title)
                        }


                    }else{
                        Box(modifier = modifier
                            .border(BorderStroke(1.dp, color = Color.Black), shape = RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Black)
                        ) {
                            Text(text = "PLACEHOLDER", color = Color.White, fontSize = 10.sp)
                        }
                    }


                }

            }


        }

    }
