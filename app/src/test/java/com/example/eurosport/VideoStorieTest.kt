package com.example.eurosport

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.eurosport.repository.dataBase.dao.manager.MediaDaoManagerImp
import com.example.eurosport.repository.network.client.MainRepository
import com.example.eurosport.repository.network.client.MainVideoStorie
import com.example.eurosport.viewModel.VideoStorieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class VideoStorieTest {
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var mainRepo: MainVideoStorie

    @Mock
    private lateinit var daoManager: MediaDaoManagerImp

    lateinit var viewModel: VideoStorieViewModel

    @Before
    fun initTest() {
        initMocks(this);
        Dispatchers.setMain(testDispatcher)
        viewModel = VideoStorieViewModel(
            ApplicationProvider.getApplicationContext(),
            MainRepository()
        )

    }

    @After
    fun shutdown() {
        testDispatcher.cleanupTestCoroutines()

    }

    @Test
    fun `read simple file`() {
        val reader = MockResponseFileReader("listVideosStories.json")
        Assert.assertNotEquals(reader.content, "")
    }

}



