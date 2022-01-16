package com.elbek.ussdcode.adapters.RvAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.elbek.ussdcode.R
import com.elbek.ussdcode.databinding.SmsPaketlarItemBinding
import com.elbek.ussdcode.models.Sms

class InternetAdapter(val smsList: ArrayList<Sms>, var onItemClickListener: OnItemClickListener) :
RecyclerView.Adapter<InternetAdapter.SmsViewHolder>() {
    inner class SmsViewHolder(var smsItemBinding: SmsPaketlarItemBinding) :
        RecyclerView.ViewHolder(smsItemBinding.root) {
        fun onBinding(sms: Sms, position: Int) {
            smsItemBinding.smsNumber.text = sms.smsNumber.toString()
            smsItemBinding.smsName.text = sms.sms_name
            smsItemBinding.smsAbout.text = sms.smsAbout
            val isBtnVisibility: Boolean = smsList[position].btnVisibility
            if (isBtnVisibility) {
                smsItemBinding.ulanishBtn.visibility = View.VISIBLE
                smsItemBinding.iconCheck.setImageResource(R.drawable.ic_keyword_top)
            } else {
                smsItemBinding.ulanishBtn.visibility = View.GONE
                smsItemBinding.iconCheck.setImageResource(R.drawable.ic_keyword_bottom)
            }


            smsItemBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(sms, position)
                notifyItemChanged(position)
            }

            smsItemBinding.ulanishBtn.setOnClickListener {
                onItemClickListener.onItemBtnClick(sms, position, smsItemBinding.ulanishBtn)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmsViewHolder {
        return SmsViewHolder(
            SmsPaketlarItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SmsViewHolder, position: Int) {
        holder.onBinding(smsList[position], position)
    }

    override fun getItemCount() = smsList.size

    interface OnItemClickListener {
        fun onItemClick(sms: Sms, position: Int)
        fun onItemBtnClick(sms: Sms, position: Int, button: Button)
    }
}