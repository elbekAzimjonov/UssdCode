package com.elbek.ussdcode.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.elbek.ussdcode.R
import com.elbek.ussdcode.adapters.RvAdapter.UssdRvAdapter
import com.elbek.ussdcode.models.Ussd
import kotlinx.android.synthetic.main.fragment_minutlar.view.*
import kotlinx.android.synthetic.main.fragment_sms.view.*


class MinutlarFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var minRvAdapter: UssdRvAdapter
    lateinit var minList: ArrayList<Ussd>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val views = inflater.inflate(R.layout.fragment_minutlar, container, false)
        views.back_min.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        minList = ArrayList()
        minList.add(Ussd("*101#", "100 Min", getString(R.string.smsAbout), "*102#"))
        minList.add(Ussd("*102#", "200 Min", getString(R.string.smsAbout), "*102#"))
        minList.add(Ussd("*103#", "300 Min", getString(R.string.smsAbout), "*102#"))
        minList.add(Ussd("*104#", "400 Min", getString(R.string.smsAbout), "*102#"))
        minList.add(Ussd("*105#", "500 Min", getString(R.string.smsAbout), "*102#"))
        minList.add(Ussd("*106#", "600 Min", getString(R.string.smsAbout), "*102#"))
        minList.add(Ussd("*107#", "700 Min", getString(R.string.smsAbout), "*102#"))
        minList.add(Ussd("*108#", "800 Min", getString(R.string.smsAbout), "*102#"))
        minList.add(Ussd("*109#", "900 Min", getString(R.string.smsAbout), "*102#"))
        minList.add(Ussd("*200#", "920 Min", getString(R.string.smsAbout), "*102#"))
        minList.add(Ussd("*120#", "930 Min", getString(R.string.smsAbout), "*102#"))
        minList.add(Ussd("*230#", "950 Min", getString(R.string.smsAbout), "*102#"))
        recyclerView = views.findViewById(R.id.minutRecycler)
        minRvAdapter = UssdRvAdapter(minList, object : UssdRvAdapter.OnItemClickListener {
            override fun onItemClick(ussd: Ussd, position: Int) {
                val btn = minList[position]
                btn.btnVisibility = !btn.btnVisibility

            }

            override fun onItemBtnClick(ussd: Ussd, position: Int, button: Button) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel: ${ussd.ussdCode}"))
                startActivity(intent)
            }
        })
        recyclerView.adapter = minRvAdapter
        return views
    }

}