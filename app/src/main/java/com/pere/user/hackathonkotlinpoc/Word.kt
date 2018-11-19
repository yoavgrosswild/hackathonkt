package com.pere.user.hackathonkotlinpoc

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Yoav G on 14/11/2018.
 */

@Entity(tableName = "words")
data class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String)