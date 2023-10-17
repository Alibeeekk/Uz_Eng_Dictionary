package dev.alibek.uz_rulugat.data.repository

import android.app.Application
import android.content.Context
import dev.alibek.uz_rulugat.data.local.AppDataBase
import dev.alibek.uz_rulugat.model.Word

class ListRepository(private val application: Application) {
    private val wordDao = AppDataBase.appDB(application).getDao()

    fun loadWords(): List<Word> {
        return wordDao.loadWord()
    }

}