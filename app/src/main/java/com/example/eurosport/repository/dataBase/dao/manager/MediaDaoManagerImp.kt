package com.example.eurosport.repository.dataBase.dao.manager

import com.example.eurosport.repository.dataBase.entity.MediaEntity
import com.example.eurosport.repository.dataBase.entity.StorieEntity
import com.example.eurosport.repository.dataBase.entity.VideoEntity

interface MediaDaoManagerImp {

    suspend fun insertVideo(videoEntity: VideoEntity)

    suspend fun updateVideo(videoEntity: VideoEntity): Int

    suspend fun insertStori(storieEntity: StorieEntity)

    suspend fun updateStori(storieEntity: StorieEntity): Int

    suspend fun getAllStories(): List<MediaEntity>

    suspend fun getStorieByNetworkId(networkId: String): List<MediaEntity>

    suspend fun getAllVideos(): List<MediaEntity>

    suspend fun getVideoByNetworkId(networkId: String): List<MediaEntity>
}