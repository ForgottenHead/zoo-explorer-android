package com.mendelu.xstast12.zooexplorer.map

import android.content.Context
import android.graphics.Bitmap
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.models.Animal

class CustomMapRenderer(val context: Context,
                           map: GoogleMap,
                           clusterManager: ClusterManager<Animal>,
) : DefaultClusterRenderer<Animal>(context,map, clusterManager) {

    private val icons: MutableMap<String, Bitmap> = mutableMapOf()

    override fun shouldRenderAsCluster(cluster: Cluster<Animal>): Boolean {
        return cluster.size > 2
    }

    override fun onBeforeClusterItemRendered(item: Animal, markerOptions: MarkerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions)
        if (!icons.containsKey(item.animalClass)){
            when (item.animalClass) {
                "Savci (Mammalia)" -> {
                    icons[item.animalClass] = MarkerUtil
                        .createMarkerIconFromResource(context, R.drawable.mammal_marker)
                }
                "Plazi (Reptilia)" -> {
                    icons[item.animalClass] = MarkerUtil
                        .createMarkerIconFromResource(context, R.drawable.reptile_marker)
                }
                else -> {
                    icons[item.animalClass] = MarkerUtil
                        .createMarkerIconFromResource(context, R.drawable.fish_marker)
                }
            }

//            icons.put(item.name,
//                MarkerUtil.createMarkerIconFromResource(context, R.drawable.reptile_marker)
//                //MarkerUtil.createCustomMarkerFromLayout(context, item, false)
//            )
        }

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icons[item.animalClass]!!))
    }


}