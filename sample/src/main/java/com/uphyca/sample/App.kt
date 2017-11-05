package com.uphyca.sample

import android.app.Application
import com.facebook.stetho.Stetho
import io.realm.DynamicRealm
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmMigration
import timber.log.Timber
import com.uphyca.sample.room.AppDatabase
import android.arch.persistence.room.Room



class App: Application() {

    companion object {
        private lateinit var realmConfiguration: RealmConfiguration

        fun getRealm(): Realm = Realm.getDefaultInstance()//Realm.getInstance(realmConfiguration)

        fun getRoom(): Unit {

        }
    }

    override fun onCreate() {
        super.onCreate()
        setupLog()
        setupStetho()
        setupRealm()
        setupRoom()
    }

    private fun setupRoom() {

    }

    private fun setupLog() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setupRealm() {
        Timber.d("setupRealm() called")
        Realm.init(this)
        realmConfiguration = RealmConfiguration.Builder()
                .name("my_database.realm")
                .schemaVersion(1)
                .migration(MyRealmMigration()).build()
    }

    private fun setupStetho() {
        Timber.d("setupStetho() called")
        Stetho.initializeWithDefaults(this)
    }

}

class MyRealmMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
        // Do Nothing
    }
}
