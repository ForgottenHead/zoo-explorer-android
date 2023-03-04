package com.mendelu.xstast12.zooexplorer.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class InternalStorageRepositoryImpl(private val context: Context): InternalStorageRepository {
    override suspend fun saveImageToInternalStorage(fileName: String, bitmap: Bitmap): Boolean {
        return try {
            context.openFileOutput(fileName, MODE_PRIVATE).use{ stream ->
                if(!bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream)){
                    throw IOException("Couldn't save bitmap $fileName")
                }
                true
            }


        }catch (e: IOException){
            e.printStackTrace()
            return false
        }

    }

    override suspend fun getImageFromInternalStorage(fileName: String): List<Bitmap> {
        return withContext(Dispatchers.IO){
            val files = context.filesDir.listFiles()
            files?.filter { it.canRead() &&  it.name.equals(fileName, true)}?. map {
                val bytes = it.readBytes()
                BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

            }
        }?: listOf()
    }
}