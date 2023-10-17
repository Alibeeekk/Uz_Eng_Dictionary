package dev.alibek.uz_rulugat.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.alibek.uz_rulugat.model.Category
import dev.alibek.uz_rulugat.model.Word

@Database(entities = [Word::class, Category::class], version = 1, exportSchema = true)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDao(): WordDao

    companion object {
        private var dataBase: AppDataBase? = null

        fun appDB(context: Context): AppDataBase {
            if (dataBase == null) {
                dataBase = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "dict.db"
                )
                    .createFromAsset("src/release/assets/database/dict.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return dataBase!!
        }

    }


}