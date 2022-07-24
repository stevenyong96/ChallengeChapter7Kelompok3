package com.example.challengechapter7kelompok3.presenter

import com.example.challengechapter7kelompok3.model.DataMain

class MainPresenterImp(val view: MainView) : MainPresenter{
    override fun getDummyData() : ArrayList<DataMain> {
        return arrayListOf(
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Adidas Raptor", 750000,"#FF233D"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_nmd.png", "Adidas NMD1", 2850000,"#F5CA7B"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/nike_jacket.png", "Nike Jacket", 300000,"#EEEE9B"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/divo_backpack.png", "Backpack", 950000,"#98fb98"),
        )
    }
}