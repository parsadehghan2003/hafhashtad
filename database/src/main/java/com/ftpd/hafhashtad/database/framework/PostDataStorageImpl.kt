package com.ftpd.hafhashtad.database.framework

import com.ftpd.hafhashtad.database.data_source.service.PostDataStorage
import com.ftpd.hafhashtad.database.domain.RealmPost
import io.realm.kotlin.TypedRealm
import io.realm.kotlin.ext.toRealmList
import io.realm.kotlin.types.RealmList
import javax.inject.Inject

class PostDataStorageImpl @Inject constructor(
    private val databaseQueue: DatabaseQueue
) : PostDataStorage {

    override suspend fun insertOrUpdatePosts(
        realmList: RealmList<RealmPost>,
        realm: TypedRealm?
    ): RealmList<RealmPost>? = databaseQueue.writeQueue(realm) {

        realmList
    }

    override suspend fun readPosts(realm: TypedRealm?): RealmList<RealmPost>? =
        databaseQueue.readQueue(realm) {
            copyFromRealm(where(RealmPost::class).findAll()).toRealmList()
        }

//    override suspend fun deleteAllPosts(realm: TypedRealm?) {
//
//        databaseQueue.writeQueue(realm) {
//
//            realmList
//        }
//    }

}