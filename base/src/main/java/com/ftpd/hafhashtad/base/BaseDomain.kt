package com.ftpd.hafhashtad.base

interface BaseDomain {
    val actionType: ActionType
}

enum class ActionType {
    ERROR,
    GET_POSTS_RESPONSE,
    GET_POSTS_REQUEST
}
