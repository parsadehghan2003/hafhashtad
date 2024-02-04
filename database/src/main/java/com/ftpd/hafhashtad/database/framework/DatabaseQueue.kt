package com.ftpd.hafhashtad.database.framework

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.TypedRealm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

open class DatabaseQueue @Inject constructor(
    private val databaseScope: CoroutineScope,
    private val databaseQueue: ExecutorCoroutineDispatcher,
    private val realmConfig: RealmConfiguration,
) {
    
    suspend fun <T> writeQueue(
        realm: TypedRealm? = null,
        run: suspend TypedRealm.() -> T?
    ): T? {
        var realmToReturn: T? = null
        if (realm == null) {
            databaseScope.launch(databaseQueue) {
                val database = Realm.open(realmConfig)
                realmToReturn = run(database)
                database.writeBlocking {
                    realmToReturn?.let {
                        when (realmToReturn) {
                            is RealmObject -> {
                                copyToRealm(realmToReturn as RealmObject, UpdatePolicy.ALL)
                            }

                            is RealmList<*> -> {
                                (realmToReturn as RealmList<RealmObject>).forEach { unManagedRealmObject ->
                                    copyToRealm(unManagedRealmObject, UpdatePolicy.ALL)
                                }
                            }

                            else -> {
                                realmToReturn
                            }
                        }
                    }
                }
                database.close()
            }.join()
        } else {
            realmToReturn = run(realm)
        }

        return realmToReturn
    }

    suspend fun <T> readQueue(realm: TypedRealm?, run: suspend TypedRealm.() -> T?): T? {
        var realmToReturn: T? = null
        if (realm == null) {
            databaseScope.launch(databaseQueue) {
                val database = Realm.open(realmConfig)
                realmToReturn = run(database)

                database.close()
            }.join()
        } else {
            realmToReturn = run(realm)
        }
        return realmToReturn
    }
}