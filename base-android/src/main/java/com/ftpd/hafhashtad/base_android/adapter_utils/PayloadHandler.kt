package com.ftpd.hafhashtad.base_android.adapter_utils

data class Change<out T>(
        val oldData: T,
        val newData: T
)

fun <T> createCombinedPayload(payloads: List<Change<T>>): Change<T> {
    assert(payloads.isNotEmpty())
    val firstChange = payloads.first()
    val lastChange = payloads.last()

    return Change(firstChange.oldData, lastChange.newData)
}
