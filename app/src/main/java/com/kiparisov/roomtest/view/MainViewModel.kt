package com.kiparisov.roomtest.view

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kiparisov.roomtest.room.account.AccountRepository
import com.kiparisov.roomtest.pojo.Account

class MainViewModel(
    private val sharedPreferences: SharedPreferences,
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _isUserSigned = MutableLiveData<Boolean>()
    val isUserSigned: LiveData<Boolean>
        get() = _isUserSigned

    private val _user = MutableLiveData<Account>()
    val user: LiveData<Account>
        get() = _user

    init {
        _user.value = sharedPreferences.getString("email", null)?.let {
            accountRepository.getAccountByEmail(it)
        }
    }

    fun signOut(){
        sharedPreferences.edit().putString("email", null).apply()
        sharedPreferences.edit().putBoolean("isUserSigned", false).apply()
        _isUserSigned.value = false
    }


}