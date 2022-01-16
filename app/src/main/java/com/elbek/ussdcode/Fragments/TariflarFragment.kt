package com.elbek.ussdcode.Fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.elbek.ussdcode.R
import com.elbek.ussdcode.adapters.RvAdapter.InternetAdapter
import com.elbek.ussdcode.adapters.TariflarRvAdapter
import com.elbek.ussdcode.models.Sms
import com.elbek.ussdcode.models.Tariflar
import kotlinx.android.synthetic.main.fragment_sms.view.*
import kotlinx.android.synthetic.main.fragment_tariflar.view.*

class TariflarFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var tariflarRvAdapter: TariflarRvAdapter
    lateinit var tariflarList: ArrayList<Tariflar>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val views = inflater.inflate(R.layout.fragment_tariflar, container, false)
        views.back_status.setNavigationOnClickListener {
            findNavController().popBackStack()
        }


        tariflarList = ArrayList()
        tariflarList.add(Tariflar("Oddiy 10", getString(R.string.smsAbout), "*1*10#"))
        tariflarList.add(Tariflar("Delovoy", getString(R.string.smsAbout), "*1*10#"))
        tariflarList.add(Tariflar("Komfort", getString(R.string.smsAbout), "*1*10#"))
        tariflarList.add(Tariflar("Milliy 10", getString(R.string.Milliy), "*111*1*11*1#"))
        tariflarList.add(Tariflar("Units 700", getString(R.string.units), "*777*1#"))
        tariflarList.add(Tariflar("Talim", getString(R.string.smsAbout), "*1*10#"))
        val chekStatus = views.findViewById<Button>(R.id.checkStatus)
        chekStatus.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel: *107#"))
            startActivity(intent)
        }
        recyclerView = views.findViewById(R.id.tariflarRecycler)
        tariflarRvAdapter =
            TariflarRvAdapter(tariflarList, object : TariflarRvAdapter.OnItemClickListener {
                override fun onItemClick(tariflar: Tariflar, position: Int) {
                    val btn = tariflarList[position]
                    btn.btnVisibility = !btn.btnVisibility

                }

                override fun onItemBtnClick(tariflar: Tariflar, position: Int, button: Button) {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.setData(Uri.parse("tel: ${tariflar.tarifCode}"))
                    startActivity(intent)
                }
            })
        recyclerView.adapter = tariflarRvAdapter
        return views
    }
}