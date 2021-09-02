package com.example.eurosport

import com.example.eurosport.repository.VideosAndStoriesModel
import com.example.eurosport.utils.convertToMediaPresentation
import com.example.eurosport.view.MediaPresentation
import com.google.gson.Gson
import java.security.MessageDigest

object MediaHelper {

    fun createMedia(): List<MediaPresentation> {
        val list = ArrayList<MediaPresentation>()
        val videosAndStoriesModel = MockResponseFileReader("listVideosStories.json")
        val test = Gson().fromJson<VideosAndStoriesModel>(
            videosAndStoriesModel.content,
            VideosAndStoriesModel::class.java
        )

        list.add(test.stories[0].convertToMediaPresentation())
        list.add(test.videos[0].convertToMediaPresentation())
        list.add(test.stories[0].convertToMediaPresentation())
        return list
    }
}