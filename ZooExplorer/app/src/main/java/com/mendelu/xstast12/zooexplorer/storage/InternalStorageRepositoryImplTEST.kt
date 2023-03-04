package com.mendelu.xstast12.zooexplorer.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class InternalStorageRepositoryImplTEST: InternalStorageRepository {
    override suspend fun saveImageToInternalStorage(fileName: String, bitmap: Bitmap): Boolean {
        return true
    }

    override suspend fun getImageFromInternalStorage(fileName: String): List<Bitmap> {
        val bm = Bitmap.createBitmap(
            10,
            10,
            Bitmap.Config.ARGB_8888
        )
        return listOf(bm)
    }
}