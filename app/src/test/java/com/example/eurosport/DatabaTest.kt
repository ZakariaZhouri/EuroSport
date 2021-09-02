package com.example.eurosport

import androidx.test.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.eurosport.repository.dataBase.MediaDataBase
import com.example.eurosport.repository.dataBase.dao.manager.MediaDaoManager
import com.example.eurosport.repository.dataBase.entity.MediaEntity
import com.example.eurosport.repository.dataBase.entity.StorieEntity
import com.example.eurosport.repository.dataBase.entity.VideoEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var mediaEntity: MediaEntity
    private lateinit var mediaDao: MediaDaoManager

    @Before
    fun setup() {
        mediaDao = MediaDaoManager(
            MediaDataBase.getInstance(
                InstrumentationRegistry.getTargetContext()
            ).getTransactionDao()
        )
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `test insertion storie in database`() = runBlocking {
        withContext(Dispatchers.Main) {
            mediaEntity = StorieEntity()
            mediaEntity.networkId = 11111
            mediaEntity.title = "Stori"
            mediaEntity.date = 1588104445.007.toFloat()
            mediaEntity.sportId = 1111
            mediaEntity.sportName = "Foot"
            insertStorie(mediaEntity as StorieEntity).await()
            val listStories = getAllStories().await()
            Assert.assertEquals(
                listStories.size,
                1
            )
        }

    }

    @Test
    fun `test insertion video in database`() = runBlocking {
        withContext(Dispatchers.Main) {
            mediaEntity = VideoEntity()
            mediaEntity.networkId = 11111
            mediaEntity.title = "Stori"
            mediaEntity.date = 1588104445.007.toFloat()
            mediaEntity.sportId = 1111
            mediaEntity.sportName = "Foot"
            insertVideo(mediaEntity as VideoEntity).await()
            val listVideoEntity = getAllVideo().await()
            Assert.assertEquals(
                listVideoEntity.size,
                1
            )
        }

    }

    private suspend fun insertStorie(storieEntity: StorieEntity) =
        GlobalScope.async(Dispatchers.Default) {
            mediaDao?.mediaDao.insertStorie(storieEntity)
        }

    private suspend fun insertVideo(videoEntity: VideoEntity) =
        GlobalScope.async(Dispatchers.Default) {
            mediaDao?.mediaDao.insertVideo(videoEntity)
        }

    private suspend fun getAllStories() =
        GlobalScope.async(Dispatchers.Default) {
            mediaDao?.mediaDao.getAllStories()
        }

    private suspend fun getAllVideo() =
        GlobalScope.async(Dispatchers.Default) {
            mediaDao?.mediaDao.getAllVideos()
        }

    /*private suspend fun getTransactionByPaymentId(payementId: String) =
        GlobalScope.async(Dispatchers.Default) {
            mediaDao?.transactionDao.getTransactionByPaymentId(payementId)
        }

    private suspend fun updateTransaction(transactionEntity: TransactionEntity) =
        GlobalScope.async(Dispatchers.Default) {
            mediaDao?.transactionDao.updateTransaction(transactionEntity)
        }

    private suspend fun deleteTransaction(transactionEntity: TransactionEntity) =
        GlobalScope.async(Dispatchers.Default) {
            mediaDao?.transactionDao.deleteTransaction(transactionEntity)
        }

    private suspend fun getAllTransactions() = GlobalScope.async(Dispatchers.Default) {
        mediaDao?.transactionDao.getAllTransactions()
    }*/
}