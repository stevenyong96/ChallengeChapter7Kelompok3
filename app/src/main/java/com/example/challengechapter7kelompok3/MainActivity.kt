package com.example.challengechapter7kelompok3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengechapter7kelompok3.adapter.MainItemApiAdapter
import com.example.challengechapter7kelompok3.databinding.*
import com.example.challengechapter7kelompok3.model.DataMain
import com.example.challengechapter7kelompok3.viewModel.MainViewModel


class MainActivity : AppCompatActivity()  {

    //step 2 untuk viewbinding, deklarasikan variable berdasarkan xml (setting gradle terlebih dahulu)
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding : FragmentMainContentBinding
    private lateinit var bindingMenu : ActivityMainBinding
    private lateinit var bindingNav : ActivityMainNavBinding
    private lateinit var bindingItemDetail : ActivityItemDetailBinding
    private lateinit var bindingAppBarMain : AppBarMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainItemApiAdapter

 @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // step 3 untuk viewbinding, bind xml ke variable
        bindingMenu = ActivityMainBinding.inflate(layoutInflater)
        binding = FragmentMainContentBinding.inflate(layoutInflater)
        bindingNav = ActivityMainNavBinding.inflate(layoutInflater)
        bindingAppBarMain = AppBarMainBinding.inflate(layoutInflater)
        bindingItemDetail = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupComponent()
        observeValue()




    }


    private fun getDummyData() : ArrayList<DataMain> {
        return arrayListOf(
            DataMain("https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/adidas_backpack.png", "Adidas Raptor", 750000,"#FF233D","https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/no_images_available.png","https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/no_images_available.png",1370),
            DataMain("https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/adidas_nmd.png", "Adidas NMD1", 2850000,"#F5CA7B","https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/video/adidas_nmd.mp4","https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/no_images_available.png",1241),
            DataMain("https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/nike_jacket.png", "Nike Jacket", 300000,"#EEEE9B","https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/no_images_available.png","https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/no_images_available.png",884),
            DataMain("https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/divo_backpack.png", "Backpack", 950000,"#98fb98","https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/no_images_available.png","https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/no_images_available.png",769),
            DataMain("https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/sepatu_nike.png", "Nike Red Shoes", 2540000,"#FF233D","https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/no_images_available.png","https://raw.githubusercontent.com/stevenyong96/fastapi-project/master/images/no_images_available.png",1234),
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

//        var result = viewModel.getContentList()
//        Log.d(MainActivity::class.simpleName, "GetContentList Result: " + result.toString())

        val tempUsername = intent.getStringExtra("DATA_USER_USERNAME")

//        binding.rvListItem.adapter = MainItemAdapter(getDummyData()) {
//            Toast.makeText(this, "Item di click : ${it.itemDesc}", Toast.LENGTH_SHORT).show()
//            var intentToItemDetail= Intent(this,ItemDetailActivity::class.java)
//            intentToItemDetail.putExtra("KEY_ITEM_NAME", it.itemDesc)
//            intentToItemDetail.putExtra("KEY_ITEM_PRICE", it.itemPrice.toString())
//            intentToItemDetail.putExtra("KEY_ITEM_COLOR", it.itemColor)
//            intentToItemDetail.putExtra("KEY_ITEM_IMAGE", it.itemImage)
//            intentToItemDetail.putExtra("KEY_ITEM_IMAGE2", it.itemImage2)
//            intentToItemDetail.putExtra("KEY_ITEM_IMAGE3", it.itemImages3)
//            intentToItemDetail.putExtra("DATA_USER_USERNAME",tempUsername)
//            startActivity(intentToItemDetail)
//        }

        adapter = MainItemApiAdapter(){
            Toast.makeText(this, "Item di click : ${it.item_name}", Toast.LENGTH_SHORT).show()
            var intentToItemDetail= Intent(this,ItemDetailActivity::class.java)
            intentToItemDetail.putExtra("KEY_ITEM_NAME", it.item_name)
            intentToItemDetail.putExtra("KEY_ITEM_PRICE", it.item_price.toString())
            intentToItemDetail.putExtra("KEY_ITEM_COLOR", it.item_color)
            intentToItemDetail.putExtra("KEY_ITEM_IMAGE", it.item_images1)
            intentToItemDetail.putExtra("KEY_ITEM_IMAGE2", it.item_images2)
            intentToItemDetail.putExtra("KEY_ITEM_IMAGE3", it.item_images3)
            intentToItemDetail.putExtra("DATA_USER_USERNAME",tempUsername)
            startActivity(intentToItemDetail)
        }
        binding.rvListItem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvListItem.adapter = adapter


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

        binding.searchView.setQueryHint("Search Your Item")

        binding.searchView.setOnSearchClickListener(View.OnClickListener {
            //do what you want when search view expended
            binding.rvListItem.visibility = View.INVISIBLE
        })
        binding.searchView.setOnCloseListener(SearchView.OnCloseListener {
            //do what you want  searchview is not expanded
            binding.rvListItem.visibility = View.VISIBLE
            false
        })

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                binding.rvListItem.visibility = View.INVISIBLE
                if(newText == "")
                {
                    viewModel.getContentList()
                    binding.rvListItem.visibility = View.VISIBLE
                }
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.getSearchContentList(query)
                binding.rvListItem.visibility = View.VISIBLE
                return false
            }


        })

    }


    private fun observeValue() {
        viewModel.contentItem.observe(this) {
            binding.pbLoading.visibility = View.GONE
            adapter.addContentList(it.toMutableList())

        }

        viewModel.errorMessage.observe(this) { event ->
            binding.pbLoading.visibility = View.GONE

            event.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                Log.d(MainActivity::class.simpleName, "GetContentList Result: " + it.toString())
            }

        }
    }

    override fun onStart() {
        super.onStart()

        binding.searchView.setQuery("", false);
        binding.searchView.clearFocus();
        viewModel.getContentList()
        binding.pbLoading.visibility = View.VISIBLE
        binding.rvListItem.visibility = View.VISIBLE

    }


}

