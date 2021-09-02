package com.example.eurosport.repository.network.client

import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class MainRepository : MainVideoStorie {

    val baseUrl = "https://extendsclass.com/"

    val videosStoriesClient: VideosStoriesClient by lazy {
        apiClient()
    }

    private fun apiClient(): VideosStoriesClient {
        val client =
            OkHttpClient.Builder()
                .callTimeout(30, TimeUnit.SECONDS).connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.MINUTES)
                .retryOnConnectionFailure(false)
                .build()


        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(VideosStoriesClient::class.java)
    }

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        apiCall: suspend () -> T
    ): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        ResultWrapper.GenericError(code)
                    }
                    else -> {
                        ResultWrapper.GenericError(null)
                    }
                }
            }
        }
    }


    override suspend fun getVideosAndStories() =
        safeApiCall { videosStoriesClient.getVideosStories() }
}