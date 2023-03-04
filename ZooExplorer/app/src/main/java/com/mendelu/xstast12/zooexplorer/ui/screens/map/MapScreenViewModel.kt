package com.mendelu.xstast12.zooexplorer.ui.screens.map

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.mendelu.xstast12.zooexplorer.architecture.BaseViewModel
import com.mendelu.xstast12.zooexplorer.database.animals.IAnimalLocalRepository
import com.mendelu.xstast12.zooexplorer.models.Animal
import com.mendelu.xstast12.zooexplorer.storage.InternalStorageRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MapScreenViewModel(
    private var animalsRepository: IAnimalLocalRepository,
    private var internalStorageRepository: InternalStorageRepository
)
    : BaseViewModel() {

    val mapUiState: MutableState<MapScreenUiState> = mutableStateOf(MapScreenUiState.Start)
    var currentAnimalId: Long? = null
    var currentAnimal: Animal? = null


    init{
        launch {
            animalsRepository.getAllFlow().collect{
                mapUiState.value = MapScreenUiState.Success(it)
            }
        }
    }

    fun getPhoto(filename: String): Bitmap {
        return runBlocking {
            internalStorageRepository.getImageFromInternalStorage(filename)[0]
        }
    }


}