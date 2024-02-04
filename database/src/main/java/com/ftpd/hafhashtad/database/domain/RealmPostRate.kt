package com.ftpd.hafhashtad.database.domain

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey


open class RealmPostRate : RealmObject {
    var rate: Float = 0.0F
    var rateCount: Long = 0
}