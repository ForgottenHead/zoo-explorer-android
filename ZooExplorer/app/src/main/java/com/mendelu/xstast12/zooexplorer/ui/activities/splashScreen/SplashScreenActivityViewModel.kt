package com.mendelu.xstast12.zooexplorer.ui.activities.splashScreen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.mendelu.xstast12.zooexplorer.architecture.BaseViewModel
import com.mendelu.xstast12.zooexplorer.architecture.CommunicationResult
import com.mendelu.xstast12.zooexplorer.communication.AnimalsRemoteRepositoryImpl
import com.mendelu.xstast12.zooexplorer.database.animals.IAnimalLocalRepository
import com.mendelu.xstast12.zooexplorer.datastore.IDataStoreRepository
import com.mendelu.xstast12.zooexplorer.storage.InternalStorageRepository
import com.mendelu.xstast12.zooexplorer.storage.InternalStorageRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

val tag: String = "SplashScreenActivityVM"

class SplashScreenActivityViewModel(
    private val dataStoreRepository: IDataStoreRepository,
    private val animalLocalRepository: IAnimalLocalRepository,
    private var animalRemoteRepository: AnimalsRemoteRepositoryImpl,
    private var internalStorageRepositoryImpl: InternalStorageRepository
) : BaseViewModel() {

    private val _splashScreenState = MutableStateFlow<SplashScreenUiState>(SplashScreenUiState.Default)
    val splashScreenState: StateFlow<SplashScreenUiState> = _splashScreenState

    //TODO change to MAP<String, Bitmap>
    var imageNames: MutableList<String> = mutableListOf()
    var bitmapImages: MutableList<Bitmap> = mutableListOf()

    fun checkAppState(){
        viewModelScope.launch {
            checkDBversion()
//        }.invokeOnCompletion {
//            _splashScreenState.value = SplashScreenUiState.ContinueToApp
        }
    }

    private suspend fun setDBversion(version: Double){
        dataStoreRepository.setDatabaseVersion(version)
        Log.e("DB version succesfully updated to: ", version.toString())
    }

    private suspend fun getDBversion(): Double{
        return dataStoreRepository.getDatabaseVersion()
    }



    private fun checkDBversion(){
        launch {
            val currentDatabaseVersion = withContext(Dispatchers.IO){
                animalRemoteRepository.getDatabaseVersion()
            }

            when(currentDatabaseVersion){
                is CommunicationResult.Error -> {
                    Log.e("$tag: checkDBerror: ",
                        currentDatabaseVersion.error.toString())
                    _splashScreenState.value = SplashScreenUiState.Error(1)

                }
                is CommunicationResult.Exception -> {
                    Log.e("$tag: checkDBexception: ",
                        currentDatabaseVersion.exception.toString())
                    _splashScreenState.value = SplashScreenUiState.Error(2)
                }
                is CommunicationResult.Success -> {
                    if (getDBversion() < currentDatabaseVersion.data){
                        Log.e("$tag, Available DB version: : ",
                            currentDatabaseVersion.data.toString())
                        Log.e("$tag, Current DB version: ",
                            getDBversion().toString())

                        downloadAnimals()

                        setDBversion(currentDatabaseVersion.data)

                    }else{
                        _splashScreenState.value = SplashScreenUiState.ContinueToApp
                    }

                }
            }
        }
    }

    private fun downloadAnimals(){
        launch {
            val result = withContext(Dispatchers.IO){
                animalRemoteRepository.getAllAnimals()
            }

            //TODO change strings
            when(result){
                is CommunicationResult.Error -> {
                    _splashScreenState.value = SplashScreenUiState.Error(1)
                }
                is CommunicationResult.Exception -> {
                    _splashScreenState.value = SplashScreenUiState.Error(2)

                }
                is CommunicationResult.Success -> {
                    //TODO drop DB or update existing

                    for (animal in result.data){
                        Log.e("$tag.DB.Playground: ", animal.toString())
                        animalLocalRepository.insert(animal)
                        imageNames.addAll(animal.images)

                    }

                    Log.e("${tag}_Downloading", " Succesfull")
                    Log.e("${tag}_Images imported: ", imageNames.toString())
                    downloadImages()
                }
            }
        }
    }

    private suspend fun downloadImages() {
        val maxSize = 5L * 1024 * 1024
        val storageRef = Firebase.storage.reference

        launch {
            val result = withContext(Dispatchers.IO) {
                for (name in imageNames){
                    downloadImage(name, maxSize, storageRef)
                }
                return@withContext true

            }

            if (result){
                Log.e("${tag}_Images downloaded and converteed: ", bitmapImages.toString())
                _splashScreenState.value = SplashScreenUiState.ContinueToApp
            }else{
                _splashScreenState.value = SplashScreenUiState.Error(400)
            }

        }

    }

    private suspend fun downloadImage(name: String, maxsize: Long, storageRef: StorageReference){
        withContext(Dispatchers.IO){
            try {
                val currentAnimal = storageRef.child("images/${name}")
                    .getBytes(maxsize).await()
                val bmp = BitmapFactory
                    .decodeByteArray(currentAnimal, 0, currentAnimal.size)
                internalStorageRepositoryImpl.saveImageToInternalStorage(name, bmp)

            } catch (e: Exception) {
                Log.e("Firebase storage download exception", e.toString())
            }
        }
    }
}