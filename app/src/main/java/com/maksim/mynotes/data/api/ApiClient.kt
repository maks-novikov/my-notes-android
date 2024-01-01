package com.maksim.mynotes.data.api

import com.maksim.mynotes.domain.AsyncResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class ApiClient(
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun <DATA> execute(
        requestBlock: suspend () -> DATA,
    ): AsyncResult<DATA> {
        return withContext(dispatcher) {
            try {
                val response = requestBlock()
                AsyncResult.success(response)
            } catch (e: Throwable) {
                AsyncResult.error(e)
            }
        }
    }
}