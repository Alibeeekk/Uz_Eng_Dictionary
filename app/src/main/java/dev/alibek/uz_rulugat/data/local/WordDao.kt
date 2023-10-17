package dev.alibek.uz_rulugat.data.local

import androidx.room.Dao
import androidx.room.Query
import dev.alibek.uz_rulugat.model.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM words")
    fun loadWord(): List<Word>



}