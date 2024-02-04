package com.ftpd.hafhashtad.base.error_handler

class HttpServerError(private val errorCode: Int) : IError {
    override fun createError(): ErrorModel {
        return when (errorCode) {
            0 -> ErrorModel(ErrorModel.ErrorStatus.TIMEOUT, "", ErrorType.HTTP_ERROR)
            400 -> ErrorModel(ErrorModel.ErrorStatus.BAD_REQUEST, "", ErrorType.HTTP_ERROR)
            404 -> ErrorModel(ErrorModel.ErrorStatus.NOT_FOUND, "", ErrorType.HTTP_ERROR)
            403 -> ErrorModel(ErrorModel.ErrorStatus.UNAUTHORIZED, "", ErrorType.HTTP_ERROR)
            else -> ErrorModel(ErrorModel.ErrorStatus.NOT_DEFINED, "", ErrorType.HTTP_ERROR)
        }

    }
}