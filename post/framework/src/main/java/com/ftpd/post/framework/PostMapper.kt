package com.ftpd.post.framework

import com.ftpd.hafhashtad.base.models.PostData
import com.ftpd.hafhashtad.base.models.PostRate
import com.ftpd.hafhashtad.base.models.PostsObject
import com.ftpd.hafhashtad.database.domain.RealmPost
import com.ftpd.hafhashtad.database.domain.RealmPostRate
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList


fun createPostObjectWithRealmPost(realmObject: RealmPost): PostData {
    return PostData().apply {
        id = realmObject.id
        title = realmObject.title
        price = realmObject.price
        description = realmObject.description
        image = realmObject.image
        category = realmObject.category
        realmObject.rating?.also {
            rating = PostRate().apply {
                rate = it.rate
                count = it.rateCount
            }
        }
    }
}

fun createRealmPostWithPostObject(postData: PostData): RealmPost {
    return RealmPost().apply {
        id = postData.id
        title = postData.title
        price = postData.price
        description = postData.description
        image = postData.image
        category = postData.category
        postData.rating?.also {
            rating = RealmPostRate().apply {
                rate = it.rate
                rateCount = it.count
            }
        }
    }
}

fun createPostRealmListWithPostsObject(list: PostsObject): RealmList<RealmPost> {
    val realmPlaces = realmListOf<RealmPost>()
    list.list.map {
        realmPlaces.add(
            createRealmPostWithPostObject(it)
        )
    }
    return realmPlaces
}


fun createPostsObjectWithPostRealmList(list: RealmList<RealmPost>): PostsObject {
    val placeObjects = PostsObject()
    list.map {
        placeObjects.list.add(
            createPostObjectWithRealmPost(it)
        )
    }
    return placeObjects
}
