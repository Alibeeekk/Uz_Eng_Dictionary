package dev.alibek.uz_rulugat.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class SharedPrefManager(val context: Context) {
    val pref = context.getSharedPreferences("MyPrefShared", AppCompatActivity.MODE_PRIVATE)

    fun saveLanguage(language: String) {

        val editor = pref.edit()
        editor.putString("Language", language)
        editor.apply()

    }

    fun getMyLanguage(): String {
        val language = pref.getString("Language", "uz")
        return language!!
    }
}
