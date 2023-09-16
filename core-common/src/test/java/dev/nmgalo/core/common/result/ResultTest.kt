package dev.nmgalo.core.common.result

import app.cash.turbine.test
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ResultTest {

    @Test
    fun `should return success result for success result`() = runTest {
        flow {
            emit("Something")
        }.asResult()
            .test {
                assertEquals(Result.Loading, awaitItem())

                when (val result = awaitItem()) {
                    Result.Loading, is Result.Error -> error("Illegal test state")
                    is Result.Success -> assertEquals("Something", result.data)
                }
                awaitComplete()
            }
    }

    @Test
    fun `should return error for error result`() = runTest {
        flow {
            emit("Dummy")
            throw NetworkResultException()
        }.asResult()
            .test {
                assertEquals(Result.Loading, awaitItem())
                assertEquals(Result.Success("Dummy"), awaitItem())

                when (val result = awaitItem()) {
                    is Result.Error -> assertEquals("An error occurred", result.exception?.message)
                    Result.Loading, is Result.Success -> error("Illegal test state")
                }

                awaitComplete()
            }
    }
}
