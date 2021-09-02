package com.example.eurosport

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.eurosport.repository.network.client.MainRepository
import com.example.eurosport.repository.network.client.ResultWrapper
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class SafeResponseTest {

    private val dispatcher = TestCoroutineDispatcher()
    private val repository = MainRepository()

    @Test
    fun `when lambda returns successfully then it should emit the result as success`() {
        runBlockingTest {
            val lambdaResult = true
            val result = repository.safeApiCall(dispatcher) { lambdaResult }
            assertEquals(ResultWrapper.Success(lambdaResult), result)
        }
    }

    @Test
    fun `when lambda throws IOException then it should emit the result as NetworkError`() {
        runBlockingTest {
            val result = repository.safeApiCall(dispatcher) { throw IOException() }
            assertEquals(ResultWrapper.NetworkError, result)
        }
    }

}