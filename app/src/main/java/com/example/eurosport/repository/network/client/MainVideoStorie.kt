package com.example.eurosport.repository.network.client

import com.example.eurosport.repository.VideosAndStoriesModel
import retrofit2.Response

interface MainVideoStorie {

    suspend fun getVideosAndStories(): ResultWrapper<Response<VideosAndStoriesModel?>>
}