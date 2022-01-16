package com.elbek.ussdcode.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.elbek.ussdcode.R
import com.elbek.ussdcode.databinding.SliderItemBinding
import com.elbek.ussdcode.models.Slides
import com.google.android.material.slider.Slider

public class SliderPagerAdapter(
    val list: ArrayList<Slides>,
    var onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<SliderPagerAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(val sliderItemBinding: SliderItemBinding) :
        RecyclerView.ViewHolder(sliderItemBinding.root) {
        fun onBind(slides: Slides, position: Int) {
            sliderItemBinding.tarifNomi.text = slides.tarifNomi
            sliderItemBinding.tarifNarxi.text = slides.tarifNarxi
            sliderItemBinding.timeTitle.text = slides.time_title
            sliderItemBinding.mbTitle.text = slides.mb_title
            sliderItemBinding.smsTitle.text = slides.sms_title
            sliderItemBinding.tarifMB.setImageResource(slides.tarifMbImg)
            sliderItemBinding.tarifSms.setImageResource(slides.tarifSmsImg)
            sliderItemBinding.tarifTime.setImageResource(slides.tarifTimeImg)
            sliderItemBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(slides)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            SliderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(list[position], position)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onItemClick(slides: Slides)
    }
}