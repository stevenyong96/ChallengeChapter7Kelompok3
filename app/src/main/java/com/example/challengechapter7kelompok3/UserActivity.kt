package com.example.challengechapter7kelompok3

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter7kelompok3.adapter.UserAdapter
import com.example.challengechapter7kelompok3.database.UserDatabase
import com.example.challengechapter7kelompok3.databinding.ActivityUserBinding
import com.example.challengechapter7kelompok3.model.UserViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding
    private lateinit var viewModel: UserViewModel

    var dataBase: UserDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupComponent()

        dataBase = UserDatabase.getInstance(this)

        binding.rvStudent.layoutManager = LinearLayoutManager(this)

        fetchData()

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }

        binding.srlStudent.setOnRefreshListener {
            fetchData()
        }

    }

//    override fun onResume() {
//        super.onResume()
//
//        binding.cpiLoading.show()
//
//        fetchData()
//    }

    private fun showSkeleton() {
        binding.rvStudent.visibility = View.GONE
        binding.skeleton.visibility = View.VISIBLE
        binding.skeleton.showSkeleton()

    }

    private fun hideSkeleton() {
        binding.rvStudent.visibility = View.VISIBLE
        binding.skeleton.visibility = View.GONE
        binding.skeleton.showOriginal()
    }

    fun fetchData() {
        if (!binding.srlStudent.isRefreshing) showSkeleton()

        val handler = Handler()
        handler.postDelayed({

            binding.cpiLoading.hide()

            binding.srlStudent.isRefreshing = false

            hideSkeleton()

            GlobalScope.launch {
                val listStudent = dataBase?.userDao()?.getAllUser()

                runOnUiThread {
                    listStudent?.let {
                        val adapter = UserAdapter(it)
                        binding.rvStudent.adapter = adapter
                    }
                }
            }
        }, 2000)

    }

    private fun setupComponent() {
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(UserViewModel::class.java)
    }
}