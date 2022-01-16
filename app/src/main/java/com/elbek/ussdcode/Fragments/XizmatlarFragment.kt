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
import com.elbek.ussdcode.adapters.TariflarRvAdapter
import com.elbek.ussdcode.models.Tariflar
import kotlinx.android.synthetic.main.fragment_sms.view.*
import kotlinx.android.synthetic.main.fragment_xizmatlar.view.*

class XizmatlarFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var tariflarRvAdapter: TariflarRvAdapter
    lateinit var tariflarList: ArrayList<Tariflar>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val views = inflater.inflate(R.layout.fragment_xizmatlar, container, false)
        tariflarList = ArrayList()
        tariflarList.add(Tariflar("LTE 4G", getString(R.string.smsAbout), "*1*10#"))
        tariflarList.add(Tariflar("Men kimman", getString(R.string.smsAbout), "*1*10#"))
        tariflarList.add(Tariflar("Menga qo’ng’iroq qiling", getString(R.string.smsAbout), "*1*10#"))
        tariflarList.add(Tariflar("«Limitsiz ovoz» xizmati", getString(R.string.smsAbout), "*1*10#"))
        tariflarList.add(Tariflar("Qo‘llab yubor", getString(R.string.smsAbout), "*1*10#"))
        tariflarList.add(Tariflar("Tungi Internet", getString(R.string.smsAbout), "*1*10#"))
        tariflarList.add(Tariflar("EXTRA balans", getString(R.string.smsAbout), "*1*10#"))
        tariflarList.add(Tariflar("Tungi qo’ng’iroqlar", getString(R.string.smsAbout), "*1*10#"))
        tariflarList.add(Tariflar("Restart", getString(R.string.smsAbout), "*1*10#"))
        views.back_xizmatlar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        recyclerView = views.findViewById(R.id.xizmatlarRecycler)
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