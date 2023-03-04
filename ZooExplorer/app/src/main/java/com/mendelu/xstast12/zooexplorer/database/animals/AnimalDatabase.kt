package com.mendelu.xstast12.zooexplorer.database.animals

import android.content.Context
import androidx.room.*
import com.mendelu.xstast12.zooexplorer.extensions.Converters
import com.mendelu.xstast12.zooexplorer.models.Animal

@Database(entities = [Animal::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AnimalDatabase : RoomDatabase() {

    abstract fun AnimalDao(): AnimalDao

    companion object {
        private var INSTANCE: AnimalDatabase? = null

        fun getDatabase(context: Context): AnimalDatabase {
            if (INSTANCE == null) {
                synchronized(AnimalDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AnimalDatabase::class.java, "animal_database",
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}