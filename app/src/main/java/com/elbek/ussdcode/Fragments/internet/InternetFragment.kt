package com.elbek.ussdcode.Fragments.internet

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.elbek.ussdcode.Fragments.sms.SmsDayFragment
import com.elbek.ussdcode.Fragments.sms.SmsFragmentWeek
import com.elbek.ussdcode.Fragments.sms.SmsMonthFragment
import com.elbek.ussdcode.R
import com.elbek.ussdcode.adapters.FragmentAdapter
import com.elbek.ussdcode.adapters.FragmentAdapterInternet
import com.elbek.ussdcode.databinding.FragmentInternetBinding
import com.elbek.ussdcode.databinding.FragmentInternetDayBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_internet.view.*
import kotlinx.android.synthetic.main.fragment_sms.view.*
import kotlinx.android.synthetic.main.fragment_sms.view.back

class InternetFragment : Fragment() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var item1: TextView
    lateinit var item2: TextView
    lateinit var item3: TextView
    lateinit var item4: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val views = inflater.inflate(R.layout.fragment_internet, container, false)
        viewPager = views.viewPagerFragmentInternet
        item1 = views.findViewById(R.id.kunlik)
        item2 = views.findViewById(R.id.haftalik)
        item3 = views.findViewById(R.id.oylik)
        item4 = views.findViewById(R.id.kunlik2)
        views.backInternet.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        views.checkStatusInternet.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel: *107#"))
            startActivity(intent)
        }

        if (viewPager.currentItem == 0) {
            customCurrent(0)
        }
        item1.setOnClickListener {
            viewPager.currentItem = 0
            customCurrent(0)
        }
        item2.setOnClickListener {
            viewPager.currentItem = 1
            customCurrent(1)
        }
        item3.setOnClickListener {
            viewPager.currentItem = 2
            customCurrent(2)
        }
        item4.setOnClickListener {
            viewPager.currentItem = 3
            customCurrent(3)
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                customCurrent(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
        setUpViewPager(viewPager)
        return views

    }

    fun customCurrent(currentItem: Int) {
        if (currentItem == 0) {
            item1.setBackgroundResource(R.drawable.visible_button)
            item1.setTextColor(Color.parseColor("#01B4FF"))
            invisibleButton(0)
        }
        if (currentItem == 1) {
            item2.setBackgroundResource(R.drawable.visible_button)
            item2.setTextColor(Color.parseColor("#01B4FF"))
            invisibleButton(1)
        }
        if (currentItem == 2) {
            item3.setBackgroundResource(R.drawable.visible_button)
            item3.setTextColor(Color.parseColor("#01B4FF"))
            invisibleButton(2)
        }
        if (currentItem == 3) {
            item4.setBackgroundResource(R.drawable.visible_button)
            item4.setTextColor(Color.parseColor("#01B4FF"))
            invisibleButton(3)
        }

    }


    private fun setUpViewPager(viewPager: ViewPager) {
        val fragmentAdapterInternet = FragmentAdapterInternet(childFragmentManager)
        fragmentAdapterInternet.addFragment(InternetDayFragment(), "Day")
        fragmentAdapterInternet.addFragment(InternetDayFragment(), "Week")
        fragmentAdapterInternet.addFragment(InternetDayFragment(), "Month")
        fragmentAdapterInternet.addFragment(InternetDayFragment(), "Week")
        viewPager.adapter = fragmentAdapterInternet
    }

    fun invisibleButton(invisibleNumber: Int) {
        if (invisibleNumber == 0) {
            item2.setBackgroundResource(R.drawable.button_custom)
            item2.setTextColor(Color.parseColor("#FFFFFF"))
            item3.setBackgroundResource(R.drawable.button_custom)
            item3.setTextColor(Color.parseColor("#FFFFFF"))
            item4.setBackgroundResource(R.drawable.button_custom)
            item4.setTextColor(Color.parseColor("#FFFFFF"))
        } else if (
            invisibleNumber == 1) {
            item1.setBackgroundResource(R.drawable.button_custom)
            item1.setTextColor(Color.parseColor("#FFFFFF"))
            item3.setBackgroundResource(R.drawable.button_custom)
            item3.setTextColor(Color.parseColor("#FFFFFF"))
            item4.setBackgroundResource(R.drawable.button_custom)
            item4.setTextColor(Color.parseColor("#FFFFFF"))
        } else if (
            invisibleNumber == 2) {
            item2.setBackgroundResource(R.drawable.button_custom)
            item2.setTextColor(Color.parseColor("#FFFFFF"))
            item1.setBackgroundResource(R.drawable.button_custom)
            item1.setTextColor(Color.parseColor("#FFFFFF"))
            item4.setBackgroundResource(R.drawable.button_custom)
            item4.setTextColor(Color.parseColor("#FFFFFF"))
        } else if (
            invisibleNumber == 3) {
            item1.setBackgroundResource(R.drawable.button_custom)
            item1.setTextColor(Color.parseColor("#FFFFFF"))
            item2.setBackgroundResource(R.drawable.button_custom)
            item2.setTextColor(Color.parseColor("#FFFFFF"))
            item3.setBackgroundResource(R.drawable.button_custom)
            item3.setTextColor(Color.parseColor("#FFFFFF"))
        }
    }
}