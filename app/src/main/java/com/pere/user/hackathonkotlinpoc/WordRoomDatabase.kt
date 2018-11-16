package com.pere.user.hackathonkotlinpoc

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

/**
 * Created by Yoav G on 16/11/2018.
 */

@Database(entities = [Word::class], version = 1)
public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao() : WordDao


    companion object {

        private var INSTANCE : WordRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope : CoroutineScope
        )
                : WordRoomDatabase {
               val tempinstance = INSTANCE
                if(tempinstance != null) {
                    return tempinstance
                }
                synchronized(this){
                    val instance = Room.databaseBuilder(context.applicationContext
                    ,WordRoomDatabase::class.java,
                        "Word_database"
                        )
                        .addCallback(WordDatabaseCallback(scope))
                        .build()
                    INSTANCE = instance
                    return instance
                }
        }
    }

    private class WordDatabaseCallback(
        private val scope : CoroutineScope

    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.wordDao())
                }
            }
        }

        fun populateDatabase(wordDao : WordDao) {
            wordDao.deleteAll()
            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("Ez")
            wordDao.insert(word)
        }
    }


}