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
import com.elbek.ussdcode.adapters.SmsAdapter
import com.elbek.ussdcode.models.Sms
import com.elbek.ussdcode.models.Ussd
import kotlinx.android.synthetic.main.fragment_sms.view.*
import kotlinx.android.synthetic.main.fragment_ussd.view.*

class UssdFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var ussdRvAdapter: UssdRvAdapter
    lateinit var ussdList: ArrayList<Ussd>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val views = inflater.inflate(R.layout.fragment_ussd, container, false)
        views.back_ussd.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        ussdList = ArrayList()
        ussdList.add(Ussd("*102#", "Balans", getString(R.string.smsAbout), "*102#"))
        ussdList.add(Ussd("*104#", "Mening Raqamim", getString(R.string.smsAbout), "*104#"))
        ussdList.add(
            Ussd(
                "*144#",
                "Menga qo’ng’iroq qiling",
                getString(R.string.smsAbout),
                "*144#"
            )
        )
        ussdList.add(
            Ussd(
                "*141#",
                "Boshqa raqamga yo’naltirish",
                getString(R.string.smsAbout),
                "*141#"
            )
        )
        ussdList.add(
            Ussd(
                "*411#",
                "Vaqtincha aloqada emasman",
                getString(R.string.smsAbout),
                "*411#"
            )
        )
        ussdList.add(Ussd("*535#", "Ko’ngilocha chat", getString(R.string.smsAbout), "*535#"))
        ussdList.add(Ussd("*111#", "Men kimman", getString(R.string.smsAbout), "*111#"))
        ussdList.add(Ussd("*096#", "Mega boom", getString(R.string.smsAbout), "*096#"))
        recyclerView = views.findViewById(R.id.ussdRecycler)
        ussdRvAdapter = UssdRvAdapter(ussdList, object : UssdRvAdapter.OnItemClickListener {
            override fun onItemClick(ussd: Ussd, position: Int) {
                val btn = ussdList[position]
                btn.btnVisibility = !btn.btnVisibility
            }
            override fun onItemBtnClick(ussd: Ussd, position: Int, button: Button) {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.setData(Uri.parse("tel: ${ussd.ussdCode}"))
                startActivity(intent)
            }
        })
        recyclerView.adapter = ussdRvAdapter
        return views
    }
}