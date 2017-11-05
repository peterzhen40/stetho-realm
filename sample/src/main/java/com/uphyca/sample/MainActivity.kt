package com.uphyca.sample

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmQuery
import timber.log.Timber
import kotlin.reflect.KClass

class MainActivity : RxAppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setup()
    }

    private fun init() {
        Timber.d("init() called")
        this.realm = App.getRealm()
    }

    private fun setup() {
        Timber.d("setup() called")
        realm.executeTransactionAsync({
            it.copyToRealmOrUpdate(Model(1, "test"))
        })

        realm.where(Model::class)
                .findFirstAsync()
                .asFlowable<Model>()
                .compose(bindToLifecycle())
                .subscribe({
                    Timber.d("realm.where(Model).findFirst() called with: model = [ $it ]")
                })
    }
}

fun <E : RealmModel> Realm.where(kClass: KClass<E>): RealmQuery<E> = where(kClass.java)
