package com.example.myapp.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.MainActivity
import com.example.myapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home2)
        Log.e("TAG", "msg oncreate")
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView: RecyclerView = findViewById(R.id.productRecyclerView)

        val products = listOf(
            Product(R.drawable.image1, "Product 1", "$10"),
            Product(R.drawable.imag2, "Product 2", "$15"),
            Product(R.drawable.imag3, "Product 3", "$20"),
            Product(R.drawable.imag3, "Product 4", "$20"),
            Product(R.drawable.imag3, "Product 5", "$20"),
            Product(R.drawable.image1, "Product 6", "$10"),
            Product(R.drawable.imag2, "Product 7", "$20"),
            Product(R.drawable.image1, "Product 8", "$10"),
            Product(R.drawable.image1, "Product 9", "$10"),
            Product(R.drawable.image1, "Product 10", "$10"),
            Product(R.drawable.image1, "Product 11", "$10"),
            Product(R.drawable.image1, "Product 12", "$10"),
            Product(R.drawable.image1, "Product 13", "$10"),

            )
        val layoutManager = GridLayoutManager(this@HomeActivity, 2)

        recyclerView.layoutManager = layoutManager

        val adapter = ProductAdapter(products)
        recyclerView.adapter = adapter
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    // Handle home item click
                    true
                }
                R.id.menu_cart -> {
                    // Handle search item click
                    true
                }
                R.id.menu_categories -> {
                    // Handle profile item click
                    true
                }
                R.id.menu_account-> {
                    val intent = Intent(this@HomeActivity, MainActivity::class.java)
                    startActivity(intent)
                    true
                    true
                }
                else -> false
            }
        }

    }

    data class Product(val image: Int, val name: String, val price: String)

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // Handle search action
                true
            }
            R.id.action_camera -> {
                val intent = Intent(this@HomeActivity, CameraPreviewActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.action_settings -> {
                // Handle settings action
                true
            }

            R.id.logout -> {Log.e("TAG", "msg  first")
                val intent = Intent(this@HomeActivity, MainActivity::class.java)
                startActivity(intent)
                true
            }

            R.id.appearance -> {
                Log.e("TAG", "msg error")
                val intent = Intent(this@HomeActivity, Dark::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}
