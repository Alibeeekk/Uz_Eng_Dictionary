package dev.alibek.uz_rulugat.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey
    val _id:Int?,
    val category_id:String?,
    val ru:String?,
    val pronounce:String?,
    val uz:String?,
    val lot:String?,
    val lot_pron:String?,
    val favorite:String?,
    val audio:String?,
    val status:String?,

)
