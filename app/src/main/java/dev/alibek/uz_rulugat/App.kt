package dev.alibek.uz_rulugat

import android.app.Application
import android.app.LocaleManager
import dev.alibek.uz_rulugat.utils.SharedPrefManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setMyLanguage()
    }

    private fun setMyLanguage() {
        val myLan = SharedPrefManager(this).getMyLanguage()
        dev.alibek.uz_rulugat.utils.LocaleManager.setLocate(this,myLan)
    }
}