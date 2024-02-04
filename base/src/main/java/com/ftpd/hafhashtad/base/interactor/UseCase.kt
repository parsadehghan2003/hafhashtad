package com.ftpd.hafhashtad.base.interactor

import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState
import com.ftpd.hafhashtad.base.error_handler.dataStateInternalErrorHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.CancellationException

abstract class UseCase<Params> {


    abstract suspend fun run(params: Params?): DataState<BaseDomain>

    suspend fun call(
        params: Params? = null
    ): Flow<DataState<BaseDomain>> = flow {
        try {
            val result =
            emit(run(params))
            println("$TAG Response: $result")
        } catch (e: CancellationException) {
            emit(dataStateInternalErrorHandler(4))
            println("$TAG Error: $e♦")
        } catch (e: Exception) {
            emit(dataStateInternalErrorHandler(-1))
            println("$TAG Error:$e cause: ${e.cause}")
        }
    }

    companion object {
        private val TAG = UseCase::class.java.name
    }




}