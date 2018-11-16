package com.pere.user.hackathonkotlinpoc

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Yoav G on 16/11/2018.
 */
class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository

    val allWords : LiveData<List<Word>>
    private var parentJob = Job()
    private val coroutineContext : CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)
    init {
        val wordDao = WordRoomDatabase.getDatabase(application, scope).wordDao()
        repository = WordRepository(wordDao)
        allWords = repository.allWords
    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    fun insert(word: Word) = scope.launch(Dispatchers.IO) {
        repository.insert(word)
    }




}