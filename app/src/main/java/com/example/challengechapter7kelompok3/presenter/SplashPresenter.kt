package com.example.challengechapter7kelompok3.presenter

interface SplashPresenter {
    fun checkVersion(localVersion: String , currentVersion: String)
    fun getCurrentVersion(localVersion: String) : String
}