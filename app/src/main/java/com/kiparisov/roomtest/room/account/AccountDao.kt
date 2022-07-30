package com.kiparisov.roomtest.room.account

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccountDao {
    @Insert
    fun createAccount(accountEntity: AccountEntity)

    @Query("SELECT * FROM accounts WHERE email = :email")
    fun getAccountByEmail(email: String): AccountEntity?

    @Query("SELECT * FROM accounts WHERE id = :id")
    fun getAccountById(id: Long): AccountEntity?
}