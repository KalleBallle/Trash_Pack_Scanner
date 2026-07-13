package com.example.packscan.Data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "figures")
data class Figure(
    @PrimaryKey
    val id: String ,
    val sculpt: String ,
    val variant: String ,
    val series: String,
    val rarity: String,
    val team: String ,
    val quantity : Int = 0 ,
    val collected : Boolean = false,
    val embedding : String? = null ,
    val imagePath : String? = null


)

