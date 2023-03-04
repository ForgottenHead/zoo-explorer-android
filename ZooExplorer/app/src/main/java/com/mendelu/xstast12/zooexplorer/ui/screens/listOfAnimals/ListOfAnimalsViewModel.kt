package com.mendelu.xstast12.zooexplorer.ui.screens.listOfAnimals

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.architecture.BaseViewModel
import com.mendelu.xstast12.zooexplorer.database.animals.IAnimalLocalRepository
import com.mendelu.xstast12.zooexplorer.models.Animal
import com.mendelu.xstast12.zooexplorer.storage.InternalStorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ListOfAnimalsViewModel(
    private var animalsLocalRepository: IAnimalLocalRepository,
    private var internalStorageRepository: InternalStorageRepository
)
    : BaseViewModel() {


    val animalsUiState: MutableState<ListOfAnimalsScreenUiState<List<Animal>>>
        = mutableStateOf(ListOfAnimalsScreenUiState.Start())


    fun fetchDatabase(){
        launch {
            animalsLocalRepository.getAllFlow().collect{
                if (it.isEmpty()){
                    animalsUiState.value = ListOfAnimalsScreenUiState.Error(R.string.empty_database)
                }else{
                    animalsUiState.value = ListOfAnimalsScreenUiState.Success(it)
                }
            }
        }
    }

    fun changeFavouriteState(id:Long, state: Boolean){
        launch { animalsLocalRepository.changeFavState(id, !state) }
    }

    fun getPhoto(filename: String): Bitmap{
        return runBlocking {
            internalStorageRepository.getImageFromInternalStorage(filename)[0]
        }
    }
}