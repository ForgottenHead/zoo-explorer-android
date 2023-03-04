package com.mendelu.xstast12.zooexplorer.ui.screens.detail

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.architecture.BaseViewModel
import com.mendelu.xstast12.zooexplorer.database.animals.IAnimalLocalRepository
import com.mendelu.xstast12.zooexplorer.models.Animal
import com.mendelu.xstast12.zooexplorer.storage.InternalStorageRepository
import com.mendelu.xstast12.zooexplorer.ui.screens.listOfAnimals.ListOfAnimalsScreenUiState
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.io.IOException

class DetailScreenViewModel(
    private var animalsLocalRepository: IAnimalLocalRepository,
    private var internalStorageRepository: InternalStorageRepository
    )
    : BaseViewModel() {
        var animalId: Long? = null
        var animalImage: Bitmap? = null


        val detailScreenUiState: MutableState<DetailScreenUiState<Animal>>
        = mutableStateOf(DetailScreenUiState.Start())


    fun fetchAnimal(){
        launch {
            val animal = animalsLocalRepository.findById(animalId!!)
            detailScreenUiState.value = DetailScreenUiState.Success(animal)
            if (animal.images.isNotEmpty()){
                animalImage = internalStorageRepository.getImageFromInternalStorage(animal.images[0])[0]

            }
        }
    }

    fun changeFavouriteState(id:Long, state: Boolean){
        launch { animalsLocalRepository.changeFavState(id, !state) }
    }

    fun getFavoritesState(id: Long): Boolean{
         return runBlocking{ animalsLocalRepository.getFavoriteState(id) } }
    }

