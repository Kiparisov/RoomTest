package com.kiparisov.roomtest.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import com.kiparisov.roomtest.R
import com.kiparisov.roomtest.databinding.ActivityMainBinding
import com.kiparisov.roomtest.room.account.AccountRepository
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            sharedPreferences = getSharedPreferences("Auth", MODE_PRIVATE),
            accountRepository = AccountRepository(this@MainActivity)
            )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.isUserSigned.observe(this){
            if (!it){
                val intent = Intent(this@MainActivity, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        val user = viewModel.user.value
        binding.mainText.text = with(user){
            "id: ${this?.id}" +
            "\nemail: ${this?.email}" +
            "\ncreatedAt: ${SimpleDateFormat("dd:MM:yyyy HH:mm").format(this?.createdAt)}"
        }

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.signOut()
        return super.onOptionsItemSelected(item)
    }
}