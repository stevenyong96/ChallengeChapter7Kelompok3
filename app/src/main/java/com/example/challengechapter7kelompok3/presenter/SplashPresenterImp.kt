package com.example.challengechapter7kelompok3.presenter

import com.example.challengechapter7kelompok3.model.DataSplash

class SplashPresenterImp(val dataView: SplashView) : SplashPresenter{
    override fun checkVersion(localVersion: String , currentVersion: String){
        val message = ""
        val model = DataSplash()
        if(localVersion === currentVersion){
            //Memanggil model Data Splash
            model.dataMessage = "UPTODATE VERSION"
            dataView.statusMessage(model)
        }
        else{
            model.dataMessage = "MISMATCH VERSION"
            dataView.statusMessage(model)
        }

    }

    override fun getCurrentVersion(localVersion: String): String {
        return localVersion
    }
}