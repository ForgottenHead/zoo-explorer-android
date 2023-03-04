package com.mendelu.xstast12.zooexplorer.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import java.io.Serializable

@Entity(tableName = "animal")
data class Animal(
    @PrimaryKey @ColumnInfo(name = "id") var id: Long,
    @ColumnInfo(name="name") var name: String,
    @ColumnInfo(name="latinName") var latinName: String,
    @ColumnInfo(name="continent") var continent: String,
    @ColumnInfo(name="animalClass") var animalClass: String,
    @ColumnInfo(name="order") var order: String,
    @ColumnInfo(name="family") var family: String,
    @ColumnInfo(name="exposition") var exposition: String?,

    @ColumnInfo(name="length") var length: String,
    @ColumnInfo(name="weight") var weight: String,
    @ColumnInfo(name="geoRange") var geoRange: String,
    @ColumnInfo(name="diet") var diet: String,
    @ColumnInfo(name="lifespan") var lifespan: String,
    @ColumnInfo(name="iucnStatus") var iucnStatus: String,

    @ColumnInfo(name="lat") var lat: Double,
    @ColumnInfo(name="long") var long: Double,
    @ColumnInfo(name="images") var images: List<String>,
    @ColumnInfo(name="model") var model: String,
    @ColumnInfo(name="isFavourite") var isFavourite: Boolean = false


) : Serializable, ClusterItem {

    override fun getPosition(): LatLng {
        return LatLng(lat, long)
    }

    override fun getTitle(): String? {
        return name
    }

    override fun getSnippet(): String? {
        if (exposition != null){
            return exposition
        }
        return "none"

    }
}
