package com.kiparisov.roomtest.view

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kiparisov.roomtest.room.account.AccountRepository

class MainViewModelFactory(
    private val sharedPreferences: SharedPreferences,
    private val accountRepository: AccountRepository
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(sharedPreferences, accountRepository) as T
    }
}