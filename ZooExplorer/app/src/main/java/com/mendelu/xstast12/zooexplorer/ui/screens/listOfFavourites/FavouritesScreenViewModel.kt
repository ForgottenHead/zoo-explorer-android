package com.mendelu.xstast12.zooexplorer.ui.screens.listOfFavourites

import android.graphics.Bitmap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.mendelu.xstast12.zooexplorer.R
import com.mendelu.xstast12.zooexplorer.architecture.BaseViewModel
import com.mendelu.xstast12.zooexplorer.database.animals.IAnimalLocalRepository
import com.mendelu.xstast12.zooexplorer.models.Animal
import com.mendelu.xstast12.zooexplorer.storage.InternalStorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class FavouritesScreenViewModel(
    private var animalsLocalRepository: IAnimalLocalRepository,
    private var internalStorageRepository: InternalStorageRepository
)
    : BaseViewModel() {

    val favouriteAnimalsUiState: MutableState<FavouritesScreenUiState<List<Animal>>>
            = mutableStateOf(FavouritesScreenUiState.Start())


    fun fetchDatabse(){
        launch {
            animalsLocalRepository.getFavouritesFlow().collect{
                if (it.isEmpty()){
                    favouriteAnimalsUiState.value = FavouritesScreenUiState.Error(R.string.empty_database)

                }else{
                    favouriteAnimalsUiState.value = FavouritesScreenUiState.Success(it)
                }
            }
        }
    }

    fun changeFavouriteState(id:Long, state: Boolean){
        launch { animalsLocalRepository.changeFavState(id, state) }
    }

    fun getPhoto(filename: String): Bitmap {
        return runBlocking {
            internalStorageRepository.getImageFromInternalStorage(filename)[0]
        }
    }
}