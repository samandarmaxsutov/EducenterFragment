package uz.gita.educenterfragment.app

import android.app.Application
import uz.gita.educenterfragment.database.AppDatabase
import uz.gita.educenterfragment.repository.impl.Repository

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.instence(this)
        Repository.instence()

    }
}