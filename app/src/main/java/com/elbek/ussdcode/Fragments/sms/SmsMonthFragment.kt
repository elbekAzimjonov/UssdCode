package com.elbek.ussdcode.Fragments.sms

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
import com.elbek.ussdcode.adapters.SmsAdapter
import com.elbek.ussdcode.models.Sms

class SmsMonthFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var smsAdapter: SmsAdapter
    lateinit var smsList: ArrayList<Sms>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val views = inflater.inflate(R.layout.fragment_sms_month, container, false)
        smsList = ArrayList()
        smsList.add(Sms(100, "SMS 100", getString(R.string.smsAbout), "*5*10#"))
        smsList.add(Sms(100, "SMS 200", getString(R.string.smsAbout), "*5*20#"))
        smsList.add(Sms(100, "SMS 300", getString(R.string.smsAbout), "*5*30#"))
        smsList.add(Sms(100, "SMS 400", getString(R.string.smsAbout), "*5*40#"))
        smsList.add(Sms(100, "SMS 500", getString(R.string.smsAbout), "*5*50#"))
        smsList.add(Sms(100, "SMS 600", getString(R.string.smsAbout), "*5*60#"))
        smsList.add(Sms(100, "SMS 700", getString(R.string.smsAbout), "*5*70#"))
        smsList.add(Sms(100, "SMS 800", getString(R.string.smsAbout), "*5*80#"))
        smsList.add(Sms(100, "SMS 900", getString(R.string.smsAbout), "*5*90#"))
        recyclerView = views.findViewById(R.id.smsRecyclerMonth)

        smsAdapter = SmsAdapter(smsList, object : SmsAdapter.OnItemClickListener {
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