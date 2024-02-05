package com.ftpd.hafhashtad.base.models

open class PostData(
    var id:Int = 0,
    var title: String = "",
    var description: String = "",
    var price: String = "",
    var image: String = "",
    var category: String = "",
    var rating: PostRate? = null
)

open class PostRate(
    var rate: Float = 0f,
    var count:Long = 0
)

open class PostsObject(
    var list: MutableList<PostData> = mutableListOf()
)