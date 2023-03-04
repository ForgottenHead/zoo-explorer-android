package com.mendelu.xstast12.zooexplorer.storage

import android.graphics.Bitmap

interface InternalStorageRepository {
    suspend fun saveImageToInternalStorage(fileName: String, bitmap: Bitmap): Boolean
    suspend fun getImageFromInternalStorage(fileName: String): List<Bitmap>
}