package dev.alibek.uz_rulugat.utils

import android.content.Context
import java.util.Locale

object LocaleManager {
    fun setLocate(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config =context.resources.configuration
        config.locale = locale
        context.resources.updateConfiguration(config, context.resources.displayMetrics)


    }
}