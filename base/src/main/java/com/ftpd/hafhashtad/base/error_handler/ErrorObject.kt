package com.ftpd.hafhashtad.base.error_handler

import com.ftpd.hafhashtad.base.ActionType
import com.ftpd.hafhashtad.base.BaseDomain

data class ErrorObject(
    val type:ErrorType = ErrorType.NOT_DEFINED,
    val message: String? = null
) : BaseDomain {
    override val actionType: ActionType
        get() = ActionType.ERROR
}