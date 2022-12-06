package kr.ac.kyungnam.android.finalproject

import android.app.Application
import java.util.prefs.Preferences

class App : Application() {
    companion object{
        lateinit var prefs:PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)

        super.onCreate()
    }
}


