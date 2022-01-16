package com.elbek.ussdcode

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.elbek.ussdcode.Fragments.HomeFragment
import com.elbek.ussdcode.Fragments.SettingsFragment
import com.elbek.ussdcode.databinding.ActivityMainBinding
import com.elbek.ussdcode.databinding.ActivityMainBinding.inflate
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.money -> {
                    
                    val intent = Intent(this, WepActivity::class.java)
                    intent.putExtra("url", "https://uztelecom.uz/")
                    startActivity(intent)

                }
                R.id.user -> {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.setData(Uri.parse("tel: 8 71 177 09 09"))
                    startActivity(intent)
                }
                R.id.notification -> {
                    val intent = Intent(this, WepActivity::class.java)
                    intent.putExtra(
                        "url",
                        "https://uztelecom.uz/uz/jismoniy-shaxslarga/internet-2/tariflar-2"
                    )
                    startActivity(intent)
                }
                R.id.settings_nav -> {
//                    val intent = Intent(this, SettingsActivity::class.java)
//                    startActivity(intent)
                }
            }
            true
        }
        bottomNavigation.menu.getItem(2).isEnabled = false

        floatingAction.setOnClickListener {

        }
    }
}