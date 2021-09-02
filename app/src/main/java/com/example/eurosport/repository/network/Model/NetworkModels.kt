package com.example.eurosport.repository

import com.fasterxml.jackson.annotation.JsonProperty

data class VideosAndStoriesModel(
    @JsonProperty("videos") val videos: List<VideoModel>,
    @JsonProperty("stories") val stories: List<StorieModel>

)

data class VideoModel(
    @JsonProperty("id") override val id: Long,
    @JsonProperty("title") override val title: String?,
    @JsonProperty("thumb") val thumb: String?,
    @JsonProperty("url") val url: String?,
    @JsonProperty("date") override val date: Float?,
    @JsonProperty("sport") override val sport: SportModel?,
    @JsonProperty("views") val views: Long?
): MediaModel

data class StorieModel(
    @JsonProperty("id") override val id: Long,
    @JsonProperty("title") override val title: String?,
    @JsonProperty("teaser") val teaser: String?,
    @JsonProperty("image") val image: String?,
    @JsonProperty("date") override val date: Float?,
    @JsonProperty("author") val author: String?,
    @JsonProperty("sport") override val sport: SportModel?
) : MediaModel

data class SportModel(
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val name: String?,
)

interface MediaModel {
    val id: Long
    val title: String?
    val date: Float?
    val sport: SportModel?
}
