package com.mendelu.xstast12.zooexplorer.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.ClusterManager
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.models.Animal

class MarkerUtil {
    companion object {

        fun createMarkerIconFromResource(context: Context, resource: Int): Bitmap {
            val drawable = ContextCompat.getDrawable(context, resource)
            drawable!!.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
            val bm = Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )

            val canvas = Canvas(bm)
            drawable.draw(canvas)
            return bm
        }

        fun setOnClickMarkerIcon(type: String, clicked: Boolean, context: Context): BitmapDescriptor{
            when(type){
                "Savci (Mammalia)" -> {
                    if (clicked){
                        return BitmapDescriptorFactory.fromBitmap(
                            createMarkerIconFromResource (context, R.drawable.mammal_marker_clicked))
                    }else{
                        return BitmapDescriptorFactory.fromBitmap(
                            createMarkerIconFromResource (context, R.drawable.mammal_marker))
                    }
                }
                "Plazi (Reptilia)" -> {
                    if (clicked){
                        return BitmapDescriptorFactory.fromBitmap(
                            createMarkerIconFromResource (context, R.drawable.reptile_marker_clicked))
                    }else{
                        return BitmapDescriptorFactory.fromBitmap(
                            createMarkerIconFromResource (context, R.drawable.reptile_marker))
                    }
                }
                else -> {
                    if (clicked){
                        return BitmapDescriptorFactory.fromBitmap(
                            createMarkerIconFromResource (context, R.drawable.fish_marker_clicked))
                    }else{
                        return BitmapDescriptorFactory.fromBitmap(
                            createMarkerIconFromResource (context, R.drawable.fish_marker))
                    }
                }
            }
        }

//        fun createCustomMarkerFromLayout(context: Context, place: Place, makeLarge: Boolean): Bitmap {
//            val layout = if (makeLarge) R.layout.map_marker_icon_large else R.layout.map_marker_icon
//            val markerView = LayoutInflater.from(context).inflate(layout, null)
//            val cardView = markerView.findViewById<CardView>(R.id.cardView)
//            val textView = markerView.findViewById<TextView>(R.id.letterText)
//            textView.text = place.title
//            cardView.setCardBackgroundColor(
//                if (place.type == 0)
//                    context.getColor(R.color.purple_200)
//                else
//                    context.getColor(R.color.purple_700))
//            markerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
//            markerView.layout(0, 0,
//                markerView.getMeasuredWidth(),
//                markerView.getMeasuredHeight()
//            )
//            markerView.buildDrawingCache()
//            val returnedBitmap = Bitmap.createBitmap(
//                markerView.getMeasuredWidth(), markerView.getMeasuredHeight(),
//                Bitmap.Config.ARGB_8888
//            )
//            val canvas = Canvas(returnedBitmap)
//            val drawable: Drawable = markerView.getBackground()
//            drawable.draw(canvas)
//            markerView.draw(canvas)
//            return returnedBitmap
//        }


    }
}