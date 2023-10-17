package dev.alibek.uz_rulugat.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(

    val cat_en: String?,
    @PrimaryKey
    val _id: Int?,
    val cat_ru: String?,
    val cat_uz: String?,
    val cat_lot: String?,
    val ordering: Int?,
    val level: String?,
    val image: String?,

    )
