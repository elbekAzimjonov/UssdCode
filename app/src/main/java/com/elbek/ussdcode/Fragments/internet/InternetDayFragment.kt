package com.elbek.ussdcode.Fragments.internet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.elbek.ussdcode.R
import com.elbek.ussdcode.adapters.RvAdapter.InternetAdapter
import com.elbek.ussdcode.adapters.SmsAdapter
import com.elbek.ussdcode.models.Sms
import kotlinx.android.synthetic.main.fragment_internet.view.*

class InternetDayFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var smsAdapter: InternetAdapter
    lateinit var smsList: ArrayList<Sms>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val views = inflater.inflate(R.layout.fragment_internet_day, container, false)

        smsList = ArrayList()
        smsList.add(Sms(50, "50 MB", getString(R.string.smsAbout), "*5*10#"))
        smsList.add(Sms(100, "100 MB", getString(R.string.smsAbout), "*5*20#"))
        smsList.add(Sms(150, "150 MB", getString(R.string.smsAbout), "*5*30#"))
        smsList.add(Sms(200, "200 MB", getString(R.string.smsAbout), "*5*40#"))
        smsList.add(Sms(250, "250 MB", getString(R.string.smsAbout), "*5*50#"))
        smsList.add(Sms(300, "300 MB", getString(R.string.smsAbout), "*5*60#"))
        smsList.add(Sms(350, "350 MB", getString(R.string.smsAbout), "*5*70#"))
        smsList.add(Sms(400, "400 MB", getString(R.string.smsAbout), "*5*80#"))
        smsList.add(Sms(450, "450 MB", getString(R.string.smsAbout), "*5*90#"))
        recyclerView = views.findViewById(R.id.interntRecyclerDay)
        smsAdapter = InternetAdapter(smsList, object : InternetAdapter.OnItemClickListener {
            override fun onItemClick(sms: Sms, position: Int) {
                val btn = smsList[position]
                btn.btnVisibility = !btn.btnVisibility

            }
            override fun onItemBtnClick(sms: Sms, position: Int, button: Button) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel: ${sms.smsCode}"))
                startActivity(intent)
            }
        })
        recyclerView.adapter = smsAdapter
       return views
    }
}