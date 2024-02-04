package com.ftpd.hafhashtad.database.domain

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey


open class RealmPost : RealmObject {

    @PrimaryKey
    var id:Int = 0
    var title: String = ""
    var description: String = ""
    var price: String = ""
    var image: String = ""
    var category: String = ""
    var rating: RealmPostRate? = null

}