package com.ftpd.hafhashtad.base

import com.ftpd.hafhashtad.base.error_handler.ErrorModel

sealed class DataState<T> {

    data class Error<T>(
        val errorObject: ErrorModel,
        var data: T? = null
    ) : DataState<T>()


    data class Data<T>(
        val data: T? = null
    ) : DataState<T>()

}
