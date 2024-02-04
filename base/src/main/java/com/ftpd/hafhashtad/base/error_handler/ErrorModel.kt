package com.ftpd.hafhashtad.base.error_handler

import com.ftpd.hafhashtad.base.ActionType
import com.ftpd.hafhashtad.base.BaseDomain

data class ErrorModel(
    var errorStatus: ErrorStatus = ErrorStatus.NONE,
    var message: String? = null,
    val errorType: ErrorType = ErrorType.NOT_DEFINED,
    override val actionType: ActionType = ActionType.ERROR

):BaseDomain {

    enum class ErrorStatus {

        NO_CONNECTION,

        BAD_RESPONSE,

        TIMEOUT,

        EMPTY_RESPONSE,

        NOT_FOUND,

        NOT_DEFINED,

        UNAUTHORIZED,

        CANCELED,

        BAD_REQUEST,

        NONE,

        NULL_POINTER,

        CAN_NOT_CAST,

        CAN_NOT_MAP,

        CANCELED_FLOW,

        DATA_STORE_ERROR

    }
}