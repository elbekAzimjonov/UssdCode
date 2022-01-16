package com.elbek.ussdcode.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.elbek.ussdcode.R
import com.elbek.ussdcode.databinding.SmsPaketlarItemBinding
import com.elbek.ussdcode.databinding.StatusItemBinding
import com.elbek.ussdcode.models.Sms
import com.elbek.ussdcode.models.Tariflar

class TariflarRvAdapter(val tariflarList: ArrayList<Tariflar>, var onItemClickListener: OnItemClickListener) :
RecyclerView.Adapter<TariflarRvAdapter.SmsViewHolder>() {
    inner class SmsViewHolder(var statusItemBinding: StatusItemBinding) :
        RecyclerView.ViewHolder(statusItemBinding.root) {
        fun onBinding(tariflar: Tariflar, position: Int) {
            statusItemBinding.smsName.text = tariflar.tarif_name
            statusItemBinding.smsAbout.text = tariflar.tarifAbout
            val isBtnVisibility: Boolean = tariflarList[position].btnVisibility
            if (isBtnVisibility) {
                statusItemBinding.ulanishBtn.visibility = View.VISIBLE
                statusItemBinding.iconCheck.setImageResource(R.drawable.ic_keyword_top)
            } else {
                statusItemBinding.ulanishBtn.visibility = View.GONE
                statusItemBinding.iconCheck.setImageResource(R.drawable.ic_keyword_bottom)
            }


            statusItemBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(tariflar, position)
                notifyItemChanged(position)
            }

            statusItemBinding.ulanishBtn.setOnClickListener {
                onItemClickListener.onItemBtnClick(tariflar, position, statusItemBinding.ulanishBtn)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsViewHolder {
        return SmsViewHolder(
           StatusItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SmsViewHolder, position: Int) {
        holder.onBinding(tariflarList[position], position)
    }

    override fun getItemCount() = tariflarList.size

    interface OnItemClickListener {
        fun onItemClick(tariflar: Tariflar, position: Int)
        fun onItemBtnClick(tariflar: Tariflar, position: Int, button: Button)
    }
}