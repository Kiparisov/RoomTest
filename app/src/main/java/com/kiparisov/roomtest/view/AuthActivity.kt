package com.kiparisov.roomtest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.kiparisov.roomtest.R
import com.kiparisov.roomtest.databinding.ActivityAuthBinding
import com.kiparisov.roomtest.room.account.AccountRepository

class AuthActivity : AppCompatActivity() {
    private val binding: ActivityAuthBinding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }

    private val viewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(
            sharedPreferences = getSharedPreferences("Auth", MODE_PRIVATE),
            accountRepository = AccountRepository(this@AuthActivity)
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.isEmailCorrect.observe(this){
            if (!it){
                Toast.makeText(this@AuthActivity, "Введите корректный Email", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isPasswordCorrect.observe(this){
            if (!it){
                Toast.makeText(this@AuthActivity, "Введите корректный пароль", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isUserAlreadyExist.observe(this){
            if (it){
                Toast.makeText(this@AuthActivity, "Пользователь уже существует", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isUserSigned.observe(this){
            if (it){
                val intent = Intent(this@AuthActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        viewModel.isIncorrectEmailOrPassword.observe(this){
            if (it){
                Toast.makeText(this@AuthActivity, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
            }
        }


        binding.btnSignIn.setOnClickListener {
            viewModel.signIn(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }

        binding.btnSignUp.setOnClickListener {
            viewModel.signUp(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }




    }
}