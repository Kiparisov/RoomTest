package com.kiparisov.roomtest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kiparisov.roomtest.room.account.AccountDao
import com.kiparisov.roomtest.room.account.AccountEntity


@Database(entities = [AccountEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    companion object{
        private var db: AppDatabase? = null
        private const val DB_NAME = "database.db"
        private val LOCK = Any()


        fun getInstance(context: Context): AppDatabase{
            db?.let { return it }

            synchronized(LOCK){
                val instance: AppDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DB_NAME
                ).allowMainThreadQueries().build()

                db = instance
                return instance
            }
        }
    }

    abstract fun getAccountDao(): AccountDao
}