package com.example.challengechapter7kelompok3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter7kelompok3.database.UserDatabase
import com.example.challengechapter7kelompok3.databinding.ActivityAddUserBinding
import com.example.challengechapter7kelompok3.entity.Users
import com.example.challengechapter7kelompok3.viewModel.RegisterViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class RegisterActivity: AppCompatActivity() {
    lateinit var binding: ActivityAddUserBinding
    var dataBase : UserDatabase? = null
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentToLogin = Intent(this, LoginActivity::class.java)

        dataBase = UserDatabase.getInstance(this)

        setupComponent()

        binding.ivBack.setOnClickListener {
            startActivity(intentToLogin)
        }

        binding.btnSubmit.setOnClickListener {
            GlobalScope.async {
                var username = binding.etUsername.text.toString()
                var password = binding.etPassword.text.toString()
                var nama = binding.etName.text.toString()
                var email = binding.etEmail.text.toString()

                Log.d(RegisterActivity::class.simpleName,"Username: " + username)
                Log.d(RegisterActivity::class.simpleName,"Password: " + password)
                Log.d(RegisterActivity::class.simpleName,"Nama: " + nama)
                Log.d(RegisterActivity::class.simpleName,"Email: " + email)
                val usersEntity = Users(username,password,nama,email)
                val result = dataBase?.userDao()?.insertUser(usersEntity)
                Log.d(RegisterActivity::class.simpleName,"Result Add User: " + result.toString())
                runOnUiThread {
                    if(result != 0.toLong() && result != -1.toLong()) {
//                        binding.etUsername.setText("")
//                        binding.etPassword.setText("")
//                        binding.etName.setText("")
//                        binding.etEmail.setText("")
                        Toast.makeText(
                            this@RegisterActivity,
                            "Insert User Success for ${username}",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(intentToLogin)
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Insert User Error for ${username}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


    private fun setupComponent() {
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(RegisterViewModel::class.java)
    }
}