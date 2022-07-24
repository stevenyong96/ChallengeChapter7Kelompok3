package com.example.challengechapter7kelompok3

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter7kelompok3.adapter.MainItemAdapter
import com.example.challengechapter7kelompok3.databinding.ActivityMainBinding
import com.example.challengechapter7kelompok3.databinding.ActivityMainNavBinding
import com.example.challengechapter7kelompok3.databinding.AppBarMainBinding
import com.example.challengechapter7kelompok3.databinding.FragmentMainContentBinding
import com.example.challengechapter7kelompok3.model.DataMain
import com.example.challengechapter7kelompok3.presenter.MainPresenterImp
import com.example.challengechapter7kelompok3.presenter.MainView
import com.example.challengechapter7kelompok3.viewModel.MainViewModel


class MainActivity : AppCompatActivity()  {

    //step 2 untuk viewbinding, deklarasikan variable berdasarkan xml (setting gradle terlebih dahulu)
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding : FragmentMainContentBinding
    private lateinit var bindingMenu : ActivityMainBinding
    private lateinit var bindingNav : ActivityMainNavBinding
    private lateinit var bindingAppBarMain : AppBarMainBinding
    private lateinit var viewModel: MainViewModel

 @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // step 3 untuk viewbinding, bind xml ke variable
        bindingMenu = ActivityMainBinding.inflate(layoutInflater)
        binding = FragmentMainContentBinding.inflate(layoutInflater)
        bindingNav = ActivityMainNavBinding.inflate(layoutInflater)
        bindingAppBarMain = AppBarMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupComponent()

        val tempUsername = intent.getStringExtra("DATA_USER_USERNAME")


//        var drawerLayout = bindingMenu.drawerLayout
//        var toolBar = binding.ivNavMenuBar
//        var actionBarDrawerToggle = ActionBarDrawerToggle
//
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();

//        //instance of database
//        val userDatabase = UserDatabase.getInstance(this)
//        val myViewModelFactory = MyViewModelFactory(userDatabase!!)
//
//        viewModel = ViewModelProvider(this, myViewModelFactory).get(MainViewModel::class.java)


        //step 4 implement recyclerview, set adapter ke recyclerview dan set layoutmanager
        binding.rvListItem.adapter = MainItemAdapter(getDummyData()) {
            Toast.makeText(this, "Item di click : ${it.itemDesc}", Toast.LENGTH_SHORT).show()
            var intentToItemDetail= Intent(this,ItemDetailActivity::class.java)
            intentToItemDetail.putExtra("KEY_ITEM_NAME", it.itemDesc)
            intentToItemDetail.putExtra("KEY_ITEM_PRICE", it.itemPrice.toString())
            intentToItemDetail.putExtra("KEY_ITEM_COLOR", it.itemColor)
            intentToItemDetail.putExtra("KEY_ITEM_IMAGE", it.itemImage)
            intentToItemDetail.putExtra("DATA_USER_USERNAME",tempUsername)
            startActivity(intentToItemDetail)
        }
        binding.rvListItem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.ivNavMenuBar.setOnClickListener {
            SharedPrefManager.setIsLandingPageShown(this,true)
            val intentToLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentToLogin)
        }

        binding.ivCartMain.setOnClickListener {
            val intentToCart = Intent(this,CartTokpeeActivity::class.java)
            intentToCart.putExtra("DATA_USER_USERNAME",tempUsername)
            startActivity(intentToCart)
        }

    }


    private fun getDummyData() : ArrayList<DataMain> {
        return arrayListOf(
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_backpack.png", "Adidas Raptor", 750000,"#FF233D"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/adidas_nmd.png", "Adidas NMD1", 2850000,"#F5CA7B"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/nike_jacket.png", "Nike Jacket", 300000,"#EEEE9B"),
            DataMain("https://raw.githubusercontent.com/stevenyong96/ChallengeChapter6Kelompok3/master/app/src/main/res/drawable/divo_backpack.png", "Backpack", 950000,"#98fb98"),
        )
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }

    private fun setupComponent() {
        viewModel = ViewModelProvider(this, defaultViewModelProviderFactory).get(MainViewModel::class.java)
    }

}

