package com.ftpd.hafhashtad.base.error_handler

abstract class ErrorHandler {
    fun createError(): ErrorModel {
        val iError = createErrorStatus()
        return iError.createError()
    }

    abstract fun createErrorStatus(): IError
}