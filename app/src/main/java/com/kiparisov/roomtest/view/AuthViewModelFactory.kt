package com.kiparisov.roomtest.view

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kiparisov.roomtest.room.account.AccountRepository

class AuthViewModelFactory(
    private val sharedPreferences: SharedPreferences,
    private val accountRepository: AccountRepository
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(sharedPreferences, accountRepository) as T
    }
}