package com.example.eurosport.utils

import com.example.eurosport.repository.MediaModel
import com.example.eurosport.repository.SportModel
import com.example.eurosport.repository.StorieModel
import com.example.eurosport.repository.VideoModel
import com.example.eurosport.repository.dataBase.entity.MediaEntity
import com.example.eurosport.repository.dataBase.entity.StorieEntity
import com.example.eurosport.repository.dataBase.entity.VideoEntity
import com.example.eurosport.view.MediaPresentation
import com.example.eurosport.view.MediaType
import com.fasterxml.jackson.annotation.JsonProperty

fun VideoModel.convertToEntity(): VideoEntity {
    val videoEntity = VideoEntity()
    videoEntity.thumb = thumb
    videoEntity.url = url
    videoEntity.networkId = id
    videoEntity.title = title
    videoEntity.date = date
    videoEntity.sportName = sport?.name
    videoEntity.sportId = sport?.id
    videoEntity.views = views
    return videoEntity
}


fun StorieModel.convertToEntity(): StorieEntity {
    val storieEntity = StorieEntity()
    storieEntity.networkId = id
    storieEntity.title = title
    storieEntity.teaser = teaser
    storieEntity.image = image
    storieEntity.date = date
    storieEntity.author = author
    storieEntity.sportName = sport?.name
    storieEntity.sportId = sport?.id
    return storieEntity
}

fun VideoModel.convertToMediaPresentation() = MediaPresentation(
    teaser = null,
    image = thumb,
    author = null,
    videoUrl = url,
    networkId = id,
    thumb = thumb,
    title = title,
    date = date,
    views = views,
    sportId = sport?.id,
    sportName = sport?.name,
    mediaType = MediaType.VIDEO
)

fun StorieModel.convertToMediaPresentation() = MediaPresentation(
    teaser = teaser,
    image = image,
    author = author,
    networkId = id,
    thumb = null,
    title = title,
    date = date,
    sportId = sport?.id,
    sportName = sport?.name,
    mediaType = MediaType.STORIE
)

fun VideoEntity.convertToMediaPresentation() = MediaPresentation(
    teaser = null,
    image = null,
    author = null,
    networkId = networkId,
    views = views,
    thumb = thumb,
    title = title,
    date = date,
    sportId = sportId,
    sportName = sportName,
    mediaType = MediaType.VIDEO
)

fun StorieEntity.convertToMediaPresentation() = MediaPresentation(
    teaser = teaser,
    image = image,
    author = author,
    networkId = networkId,
    thumb = null,
    title = title,
    date = date,
    sportId = sportId,
    sportName = sportName,
    mediaType = MediaType.STORIE
)

