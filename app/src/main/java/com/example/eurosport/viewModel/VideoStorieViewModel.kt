package com.example.eurosport.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.eurosport.repository.StorieModel
import com.example.eurosport.repository.VideoModel
import com.example.eurosport.repository.dataBase.MediaDataBase
import com.example.eurosport.repository.dataBase.dao.manager.MediaDaoManager
import com.example.eurosport.repository.dataBase.dao.manager.MediaDaoManagerImp
import com.example.eurosport.repository.dataBase.entity.MediaEntity
import com.example.eurosport.repository.dataBase.entity.StorieEntity
import com.example.eurosport.repository.dataBase.entity.VideoEntity
import com.example.eurosport.repository.network.client.MainRepository
import com.example.eurosport.repository.network.client.ResultWrapper
import com.example.eurosport.utils.convertToEntity
import com.example.eurosport.utils.convertToMediaPresentation
import com.example.eurosport.view.MediaPresentation
import kotlinx.coroutines.*

class VideoStorieViewModel(
    private val context: Context?,
    private val mainRepository: MainRepository
) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    var job: Job? = null
    val movieList = MutableLiveData<List<MediaPresentation>>()
    private val mediaManagerImp: MediaDaoManagerImp = MediaDaoManager(
        MediaDataBase.getInstance(context).getTransactionDao()
    )

    fun getVideosStories() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getVideosAndStories()
            withContext(Dispatchers.Main) {
                System.out.println("Start Test2" );

                when (response) {
                    is ResultWrapper.GenericError -> handleOfflineMode("Network Error")
                    ResultWrapper.NetworkError -> handleOfflineMode("Network Error")
                    is ResultWrapper.Success -> {
                        saveVideo(response.value.body()?.videos)
                        saveStories(response.value.body()?.stories)
                        movieList.value = createTableForView(
                            response.value.body()?.stories,
                            response.value.body()?.videos
                        )
                    }
                }
            }
        }
    }

    private suspend fun saveStories(stories: List<StorieModel>?) {
        stories?.let { listStories ->
            for (storie in listStories) {
                mediaManagerImp.insertStori(storie.convertToEntity())
            }
        }
    }

    private suspend fun saveVideo(listVideo: List<VideoModel>?) {
        listVideo?.let { listVideo ->
            for (video in listVideo) {
                mediaManagerImp.insertVideo(video.convertToEntity())
            }
        }
    }

    private suspend fun createTableForView(
        stories: List<StorieModel>?,
        videos: List<VideoModel>?
    ): ArrayList<MediaPresentation> {
        val first = stories?.iterator()
        val second = videos?.iterator()
        val list = ArrayList<MediaPresentation>()
        while (first?.hasNext()!! || second?.hasNext()!!) {
            if (first.hasNext())
                list.add(first.next().convertToMediaPresentation())
            if (second?.hasNext()!!)
                list.add(second.next().convertToMediaPresentation())
        }

        return list
    }

    private suspend fun handleOfflineMode(error: String) {
        val listStories = mediaManagerImp.getAllStories()
        val listVideo = mediaManagerImp.getAllVideos()
        if (listStories.isEmpty() && listVideo.isEmpty()) {
            errorMessage.postValue(error)
            return
        }
        val storiIterator = listStories.iterator()
        val videoIterator = listVideo.iterator()
        val list = ArrayList<MediaPresentation>()
        while (storiIterator.hasNext() || videoIterator.hasNext()) {
            if (storiIterator.hasNext())
                list.add((storiIterator.next() as StorieEntity).convertToMediaPresentation())
            if (videoIterator.hasNext()!!)
                list.add((videoIterator.next() as VideoEntity).convertToMediaPresentation())
        }
        movieList.postValue(list)
    }
}