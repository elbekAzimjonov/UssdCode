package com.elbek.ussdcode.Fragments.sms

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.elbek.ussdcode.Fragments.sms.SmsDayFragment
import com.elbek.ussdcode.Fragments.sms.SmsFragmentWeek
import com.elbek.ussdcode.Fragments.sms.SmsMonthFragment
import com.elbek.ussdcode.R
import com.elbek.ussdcode.adapters.FragmentAdapter
import com.elbek.ussdcode.adapters.ViewPagerAdapter
import com.elbek.ussdcode.databinding.FragmentSmsBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_sms.view.*


class SmsFragment : Fragment() {
    lateinit var viewPager: ViewPager
    lateinit var button: Button
    lateinit var item1: TextView
    lateinit var item2: TextView
    lateinit var item3: TextView
    lateinit var item4: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val views = inflater.inflate(R.layout.fragment_sms, container, false)
        viewPager = views.findViewById(R.id.viewPagerFragment)
        item1 = views.findViewById(R.id.kunlik)
        item2 = views.findViewById(R.id.haftalik)
        item3 = views.findViewById(R.id.oylik)
        item4 = views.findViewById(R.id.kunlik2)
        button = views.checkStatus
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel: *100#"))
            startActivity(intent)
        }
        views.back.setNavigationOnClickListener {
            findNavController().popBackStack()
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
        setupViewPager(viewPager)
        return views

    }

    private fun setupViewPager(viewpager: ViewPager) {
        var adapter: ViewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(SmsDayFragment(), "Day")
        adapter.addFragment(SmsFragmentWeek(), "Week")
        adapter.addFragment(SmsMonthFragment(), "Month")
        adapter.addFragment(SmsFragmentWeek(), "Week")
        // setting adapter to view pager.
        viewpager.setAdapter(adapter)
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
