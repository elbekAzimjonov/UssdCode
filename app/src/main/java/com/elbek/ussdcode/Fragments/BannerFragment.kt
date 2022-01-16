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
import android.widget.TextView
import com.elbek.ussdcode.R
import com.elbek.ussdcode.WepActivity
import com.elbek.ussdcode.models.Slides
import java.lang.reflect.InvocationTargetException

class BannerFragment : Fragment() {
    lateinit var slides: Slides
    private lateinit var statusName: String
    private lateinit var statusPrice: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_banner, container, false)
        arguments?.let { bundle ->
            slides = bundle.getParcelable("slide")!!
        }
        val tarifNomi = view.findViewById<TextView>(R.id.tarifNomi)
        val tarifNarxi = view.findViewById<TextView>(R.id.tarifNarxi)
        val tarifDaqiqa = view.findViewById<TextView>(R.id.time_title)
        val tarifMb = view.findViewById<TextView>(R.id.mb_title)
        val tarifSms = view.findViewById<TextView>(R.id.sms_title)
        val statusName = view.findViewById<TextView>(R.id.statusName)
        val abanentNarxi = view.findViewById<TextView>(R.id.abanentNarxi)
        val tarifHaqida = view.findViewById<TextView>(R.id.statusAboutt)
        val connectBtn = view.findViewById<Button>(R.id.ulanishBtn)
        val batafsilBtn = view.findViewById<Button>(R.id.batafsilBtn)
        tarifNomi.text = slides.tarifNomi
        tarifHaqida.text = slides.tarif_abaut.toString()
        tarifNarxi.text = slides.tarifNarxi
        tarifDaqiqa.text = slides.time_title
        tarifMb.text = slides.mb_title
        tarifSms.text = slides.sms_title
        statusName.text = slides.tarifNomi
        abanentNarxi.text = slides.tarifNarxi
        connectBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.setData(Uri.parse("tel: ${slides.tarif_code}"))
            startActivity(intent)
        }
        batafsilBtn.setOnClickListener {
            val intent = Intent(requireActivity(), WepActivity::class.java)
            intent.putExtra("url",slides.tarif_link)
            startActivity(intent)
        }
        return view
    }
}