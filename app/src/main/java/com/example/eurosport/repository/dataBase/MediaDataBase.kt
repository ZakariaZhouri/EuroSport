package com.example.eurosport.repository.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eurosport.repository.dataBase.dao.MediaDao
import com.example.eurosport.repository.dataBase.entity.StorieEntity
import com.example.eurosport.repository.dataBase.entity.VideoEntity

@Database(
    entities = [VideoEntity::class, StorieEntity::class],
    version = 1,
    exportSchema = false
)

abstract class MediaDataBase : RoomDatabase() {

    abstract fun getTransactionDao(): MediaDao


    companion object {
        @Volatile
        private var INSTANCE: MediaDataBase? = null

        fun getInstance(context: Context?): MediaDataBase {
            val tempInstance = INSTANCE

            tempInstance?.let { return tempInstance }

            synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        context!!.applicationContext,
                        MediaDataBase::class.java,
                        "Media_data_base"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}