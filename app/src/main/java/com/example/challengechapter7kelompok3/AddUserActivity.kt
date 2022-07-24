package com.example.challengechapter7kelompok3

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter7kelompok3.database.UserDatabase
import com.example.challengechapter7kelompok3.databinding.ActivityAddUserBinding
import com.example.challengechapter7kelompok3.entity.Users
import com.example.challengechapter7kelompok3.viewModel.AddUserViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddUserActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddUserBinding
    var dataBase : UserDatabase? = null
    private lateinit var viewModel: AddUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataBase = UserDatabase.getInstance(this)
        setupComponent()


        binding.btnSubmit.setOnClickListener {
            val username = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val nama = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            println(username)
            println(password)
            println(nama)
            println(email)

            val tempUser = Users(username, password, nama, email)

            GlobalScope.async {
                val result = dataBase?.userDao()?.insertUser(tempUser)
                runOnUiThread {
                    if (result != 0.toLong()) {
                        Toast.makeText(
                            this@AddUserActivity,
                            "Sukses menambahkan ${tempUser.nama}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@AddUserActivity,
                            "Gagal menambahkan ${tempUser.nama}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    finish()
                }
            }
        }
    }

    private fun setupComponent() {
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(AddUserViewModel::class.java)
    }
}
