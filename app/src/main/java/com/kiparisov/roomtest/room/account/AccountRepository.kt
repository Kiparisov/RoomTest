package com.kiparisov.roomtest.room.account

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.kiparisov.roomtest.pojo.Account
import com.kiparisov.roomtest.room.AppDatabase

class AccountRepository(private val context: Context) {
    private val db: AppDatabase by lazy {
        AppDatabase.getInstance(context)
    }

    fun getAccountByEmail(email: String): Account{
        return db.getAccountDao().getAccountByEmail(email).toAccount()
    }

    fun getAccountById(id: Long): Account{
        return db.getAccountDao().getAccountById(id).toAccount()
    }

    fun createAccount(account: Account){
        try {
            db.getAccountDao().createAccount(AccountEntity(
                id = account.id,
                email = account.email,
                password = account.password,
                createdAt = account.createdAt
            ))
        }catch (e: SQLiteConstraintException){
            throw SQLiteConstraintException()
        }
    }
}