package com.ftpd.hafhashtad.base.error_handler

class InternalError(
    val errorCode: Int
) : IError {
    override fun createError(): ErrorModel {
        return when(errorCode) {
            1 -> ErrorModel(ErrorModel.ErrorStatus.NULL_POINTER, errorType = ErrorType.INTERNAL_ERROR)
            2 -> ErrorModel(ErrorModel.ErrorStatus.CAN_NOT_CAST, errorType = ErrorType.INTERNAL_ERROR)
            3 -> ErrorModel(ErrorModel.ErrorStatus.CAN_NOT_MAP, errorType = ErrorType.INTERNAL_ERROR)
            4 -> ErrorModel(ErrorModel.ErrorStatus.CANCELED_FLOW, errorType = ErrorType.INTERNAL_ERROR)
            5 -> ErrorModel(ErrorModel.ErrorStatus.DATA_STORE_ERROR, errorType = ErrorType.INTERNAL_ERROR)
            else -> ErrorModel(ErrorModel.ErrorStatus.NONE, errorType = ErrorType.INTERNAL_ERROR)
        }
    }
}