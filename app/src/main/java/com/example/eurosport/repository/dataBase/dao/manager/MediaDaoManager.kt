package com.example.eurosport.repository.dataBase.dao.manager

import com.example.eurosport.repository.dataBase.dao.MediaDao
import com.example.eurosport.repository.dataBase.entity.MediaEntity
import com.example.eurosport.repository.dataBase.entity.StorieEntity
import com.example.eurosport.repository.dataBase.entity.VideoEntity

class MediaDaoManager(val mediaDao: MediaDao) : MediaDaoManagerImp {
    override suspend fun insertVideo(videoEntity: VideoEntity) {
        if (videoEntity.rowId != 0L) updateVideo(videoEntity)
        else mediaDao.insertVideo(videoEntity)
    }

    override suspend fun updateVideo(videoEntity: VideoEntity): Int {
        return mediaDao.updateVideo(videoEntity)
    }

    override suspend fun insertStori(storieEntity: StorieEntity) {
        if (storieEntity.rowId != 0L) updateStori(storieEntity)
        else mediaDao.insertStorie(storieEntity)
    }

    override suspend fun updateStori(storieEntity: StorieEntity): Int {
        return mediaDao.updateStorie(storieEntity)
    }

    override suspend fun getAllStories(): List<MediaEntity> {
        return mediaDao.getAllStories()
    }

    override suspend fun getStorieByNetworkId(networkId: String): List<MediaEntity> {
        return mediaDao.getStorieByNetworkId(networkId)
    }

    override suspend fun getAllVideos(): List<MediaEntity> {
        return mediaDao.getAllVideos()
    }

    override suspend fun getVideoByNetworkId(networkId: String): List<MediaEntity> {
        return mediaDao.getVideoByNetworkId(networkId)
    }

}