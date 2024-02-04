package com.ftpd.hafhashtad.base.error_handler

import com.ftpd.hafhashtad.base.BaseDomain
import com.ftpd.hafhashtad.base.DataState

fun errorDataState(errorHandler: ErrorHandler): DataState.Error<BaseDomain> =
    DataState.Error(errorHandler.createError())

fun dataStateInternalErrorHandler(errorCode : Int): DataState.Error<BaseDomain> =
    errorDataState(InternalErrorHandler(errorCode))

fun dataStateRemoteErrorHandler(errorCode : Int): DataState.Error<BaseDomain> =
    errorDataState(HttpErrorHandler(errorCode))
