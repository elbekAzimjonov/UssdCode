package com.elbek.ussdcode.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.elbek.ussdcode.R
import com.elbek.ussdcode.SettingsActivity
import com.elbek.ussdcode.WepActivity
import com.elbek.ussdcode.adapters.SliderPagerAdapter
import com.elbek.ussdcode.databinding.ActivityMainBinding
import com.elbek.ussdcode.databinding.FragmentHomeBinding
import com.elbek.ussdcode.models.Slides
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.slider_item.*
import java.net.URI
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask

class HomeFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: FragmentHomeBinding
    lateinit var list: ArrayList<Slides>
    lateinit var sliderPagerAdapter: SliderPagerAdapter
    lateinit var viewPager2: ViewPager2
    lateinit var indicator: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewPager2 = view.findViewById(R.id.slider_pager)
        val internet = view.findViewById<ImageView>(R.id.internet)
        val sms = view.findViewById<ImageView>(R.id.sms)
        val tarif = view.findViewById<ImageView>(R.id.tarif)
        val ussd = view.findViewById<ImageView>(R.id.ussd)
        val xizmatlar = view.findViewById<ImageView>(R.id.setting)
        val min = view.findViewById<ImageView>(R.id.minut)
        view.navigationView.setNavigationItemSelectedListener(this)
        internet.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_homeFragment_to_internetFragment)
        }
        sms.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_homeFragment_to_smsFragment)
        }
        tarif.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_homeFragment_to_tariflarFragment)
        }
        ussd.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_homeFragment_to_ussdFragment)
        }
        xizmatlar.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_homeFragment_to_xizmatlarFragment)
        }
        min.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_homeFragment_to_minutlarFragment)
        }
        view.shareTool.setNavigationOnClickListener {
            val t1 = "Hi"
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, t1)
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
        view.telegramSend.setNavigationOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/uztelecomuz"))
            startActivity(intent)
        }
        view.navigation_menu.setNavigationOnClickListener {
            view.drawerLayout.openDrawer(GravityCompat.START)
        }

        indicator = view.findViewById(R.id.indicator)
        list = ArrayList()
        list.add(
            Slides(
                "Просто 10",
                "10 000 сум в месяц",
                R.drawable.call,
                R.drawable.sms,
                R.drawable.mb,
                "10 MIN",
                "10 SMS",
                "10 SMS",
                getString(R.string.porsto10),
                "*1#",
                "https://uztelecom.uz/uz/"
            )
        )
        list.add(
            Slides(
                "Units 700",
                "7 000 so'm 7 kunga",
                R.drawable.call,
                R.drawable.sms,
                R.drawable.mb,
                "700 Units",
                "700 Units",
                "700 Units",
                getString(R.string.units),
                "*777*1# ",
                "https://uztelecom.uz/uz/jismoniy-shaxslarga/mobil-aloqa/gsm-2/tariflar/units-2-3/units-700-1"
            )
        )
        list.add(
            Slides(
                "Milliy 10 ",
                "10 000 so'm oyiga",
                R.drawable.call,
                R.drawable.sms,
                R.drawable.mb,
                "10 MIN",
                "10 SMS",
                "10 MB",
                getString(R.string.Milliy),
                "*111*1*11*1#",
                "https://uztelecom.uz/uz/jismoniy-shaxslarga/mobil-aloqa/gsm-2/tariflar/milliy-3/milliy-3-1"
            )
        )
        list.add(
            Slides(
                "Talim",
                "34 900 so'm oyiga",
                R.drawable.call,
                R.drawable.sms,
                R.drawable.mb,
                "300 MIN",
                "500 SMS",
                "8000 MB",
                getString(R.string.Talim),
                "*225*1*2#",
                "https://uztelecom.uz/uz/jismoniy-shaxslarga/mobil-aloqa/gsm-2/tariflar/imtiyozli-tarif-rejalar/talim-2"
            )
        )
        sliderPagerAdapter =
            SliderPagerAdapter(list, object : SliderPagerAdapter.OnItemClickListener {
                override fun onItemClick(slides: Slides) {
                    val slidesNew = Slides(
                        slides.tarifNomi,
                        slides.tarifNarxi,
                        slides.tarifTimeImg,
                        slides.tarifSmsImg,
                        slides.tarifMbImg,
                        slides.time_title,
                        slides.sms_title,
                        slides.mb_title,
                        slides.tarif_abaut,
                        slides.tarif_code,
                        slides.tarif_link
                    )
                    var bundle: Bundle
                    bundle = Bundle()
                    bundle = bundleOf("slide" to slides)
                    Navigation.findNavController(view)
                        .navigate(R.id.action_homeFragment_to_bannerFragment, bundle)

                }
            })
        viewPager2.adapter = sliderPagerAdapter

        TabLayoutMediator(indicator, viewPager2) { tab, position ->

        }.attach()

        return view
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_speed -> {
                val intent = Intent(requireActivity(), WepActivity::class.java)
                intent.putExtra(
                    "url",
                    "https://www.speedtest.net/ru"
                )
                startActivity(intent)
            }
            R.id.nav_send -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/uztelecomuz"))
                startActivity(intent)
            }
            R.id.nav_share -> {
                val t1 = "Hi"
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, t1)
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }
            R.id.nav_setting -> {
                val intent = Intent(requireActivity(), SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}