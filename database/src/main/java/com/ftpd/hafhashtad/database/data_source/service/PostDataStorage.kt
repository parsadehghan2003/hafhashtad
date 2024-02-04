package com.ftpd.hafhashtad.database.data_source.service

import com.ftpd.hafhashtad.database.domain.RealmPost
import io.realm.kotlin.TypedRealm
import io.realm.kotlin.types.RealmList

interface PostDataStorage {
    suspend fun insertOrUpdatePosts(
        realmList: RealmList<RealmPost>,
        realm: TypedRealm? = null
    ): RealmList<RealmPost>?

    suspend fun readPosts(realm: TypedRealm? = null): RealmList<RealmPost>?
//    suspend fun deleteAllPosts(realm: TypedRealm? = null)
}