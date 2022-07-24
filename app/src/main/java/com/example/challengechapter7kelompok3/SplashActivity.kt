package com.example.challengechapter7kelompok3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.challengechapter7kelompok3.model.DataSplash
import com.example.challengechapter7kelompok3.viewModel.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val localVer = "1.0.0"
        val currentVer = "1.0.0" //get from api version later

        setupComponent()

        val isLandingPageShown = SharedPrefManager.getIsLandingPageShown(this)
        Log.d(SplashActivity::class.simpleName,"Splash : ${isLandingPageShown}")

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000L)
            if(isLandingPageShown){
                startActivity(Intent(this@SplashActivity, LandingActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                finish()
            }

        }
    }

    private fun setupComponent() {
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(SplashViewModel::class.java)
    }
}