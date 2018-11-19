package com.pere.user.hackathonkotlinpoc

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

/**
 * Created by Yoav G on 16/11/2018.
 */
class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }


}