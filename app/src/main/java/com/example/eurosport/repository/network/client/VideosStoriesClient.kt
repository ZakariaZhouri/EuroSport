package com.example.eurosport.repository.network.client

import com.example.eurosport.repository.VideosAndStoriesModel
import retrofit2.Response
import retrofit2.http.GET

interface VideosStoriesClient {

    @GET("api/json-storage/bin/edfefba")
    suspend fun getVideosStories(): Response<VideosAndStoriesModel?>
}