package com.pere.user.hackathonkotlinpoc

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by Yoav G on 16/11/2018.
 */
@Dao
interface WordDao {

    @Query("select * from words order by word asc")
    fun getAllWords() : LiveData<List<Word>>

    @Insert
    fun insert(word: Word)

    @Query("delete from words")
    fun deleteAll()

}