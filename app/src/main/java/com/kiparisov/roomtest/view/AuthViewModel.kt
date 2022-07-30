package com.kiparisov.roomtest.view

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kiparisov.roomtest.pojo.Account
import com.kiparisov.roomtest.room.account.AccountRepository
import java.lang.Exception

class AuthViewModel(
    private val sharedPreferences: SharedPreferences,
    private val repository: AccountRepository
): ViewModel() {

    private val _isEmailCorrect = MutableLiveData<Boolean>()
    val isEmailCorrect: LiveData<Boolean>
        get() = _isEmailCorrect

    private val _isPasswordCorrect = MutableLiveData<Boolean>()
    val isPasswordCorrect: LiveData<Boolean>
        get() = _isPasswordCorrect

    private val _isUserAlreadyExist = MutableLiveData<Boolean>()
    val isUserAlreadyExist: LiveData<Boolean>
        get() = _isUserAlreadyExist

    private val _isUserSigned = MutableLiveData<Boolean>()
    val isUserSigned: LiveData<Boolean>
        get() = _isUserSigned

    private val _isIncorrectEmailOrPassword = MutableLiveData<Boolean>()
    val isIncorrectEmailOrPassword: LiveData<Boolean>
        get() = _isIncorrectEmailOrPassword


    init {
        _isUserSigned.value = sharedPreferences.getBoolean("isUserSigned", false)
    }

    fun signIn(email: String, password: String){
        val acc: Account? = repository.getAccountByEmail(email)
        acc?.let{
            if (it.password == password){
                _isUserSigned.value = true
                sharedPreferences.edit().putBoolean("isUserSigned", true).apply()
                sharedPreferences.edit().putString("email", email).apply()
            }else{
                _isIncorrectEmailOrPassword.value = true
            }
        } ?: run{
            _isIncorrectEmailOrPassword.value = true
        }

    }

    fun signUp(email: String, password: String){
        if (isDataCorrect(email, password)){
            try {
                repository.createAccount(Account(email, password, System.currentTimeMillis()))
                sharedPreferences.edit().putBoolean("isUserSigned", true).apply()
                sharedPreferences.edit().putString("email", email).apply()
                _isUserSigned.value = true
            }catch (e: Exception){
                _isUserAlreadyExist.value = true
            }

        }
    }


    private fun isDataCorrect(email: String, password: String): Boolean{
        var isDataCorrect = true

        if (email.isEmpty() || !email.contains('@')) {
            isDataCorrect = false
            _isEmailCorrect.value = false
        }

        if (password.length < 6){
            isDataCorrect = false
            _isPasswordCorrect.value = false
        }

        return isDataCorrect
    }

}