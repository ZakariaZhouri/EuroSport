package com.example.eurosport.repository.dataBase.dao

import androidx.room.*
import com.example.eurosport.repository.dataBase.entity.MediaEntity
import com.example.eurosport.repository.dataBase.entity.StorieEntity
import com.example.eurosport.repository.dataBase.entity.VideoEntity


@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(videoEntity: VideoEntity)

    @Update
    suspend fun updateVideo(storieEntity: VideoEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStorie(storieEntity: StorieEntity)

    @Update
    suspend fun updateStorie(storieEntity: StorieEntity): Int

    @Query("SELECT * FROM ${StorieEntity.STORIE_TABLE}")
    suspend fun getAllStories(): List<StorieEntity>

    @Query("SELECT * FROM ${StorieEntity.STORIE_TABLE} WHERE ${MediaEntity.COLUMN_ROW_NETWORK_ID}=:networkId")
    suspend fun getStorieByNetworkId(networkId: String): List<StorieEntity>

    @Query("SELECT * FROM ${VideoEntity.VIDEO_TABLE}")
    suspend fun getAllVideos(): List<VideoEntity>

    @Query("SELECT * FROM ${VideoEntity.VIDEO_TABLE} WHERE ${MediaEntity.COLUMN_ROW_NETWORK_ID}=:networkId")
    suspend fun getVideoByNetworkId(networkId: String): List<VideoEntity>
}