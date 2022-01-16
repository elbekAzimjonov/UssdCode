package com.elbek.ussdcode.adapters.RvAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.elbek.ussdcode.R
import com.elbek.ussdcode.databinding.SmsPaketlarItemBinding
import com.elbek.ussdcode.models.Sms
import com.elbek.ussdcode.models.Ussd

class UssdRvAdapter(val smsList: ArrayList<Ussd>, var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<UssdRvAdapter.UssdViewHolder>() {
    inner class UssdViewHolder(var smsItemBinding: SmsPaketlarItemBinding) :
        RecyclerView.ViewHolder(smsItemBinding.root) {
        fun onBinding(ussd: Ussd, position: Int) {
            smsItemBinding.smsNumber.text = ussd.ussdNumber.toString()
            smsItemBinding.smsName.text = ussd.ussd_name
            smsItemBinding.smsAbout.text = ussd.ussdAbout
            val isBtnVisibility: Boolean = smsList[position].btnVisibility
            if (isBtnVisibility) {
                smsItemBinding.ulanishBtn.visibility = View.VISIBLE
                smsItemBinding.iconCheck.setImageResource(R.drawable.ic_keyword_top)
            } else {
                smsItemBinding.ulanishBtn.visibility = View.GONE
                smsItemBinding.iconCheck.setImageResource(R.drawable.ic_keyword_bottom)
            }


            smsItemBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(ussd, position)
                notifyItemChanged(position)
            }

            smsItemBinding.ulanishBtn.setOnClickListener {
                onItemClickListener.onItemBtnClick(ussd, position, smsItemBinding.ulanishBtn)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UssdViewHolder {
        return UssdViewHolder(
            SmsPaketlarItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UssdViewHolder, position: Int) {
        holder.onBinding(smsList[position], position)
    }

    override fun getItemCount() = smsList.size

    interface OnItemClickListener {
        fun onItemClick(ussd: Ussd, position: Int)
        fun onItemBtnClick(ussd: Ussd, position: Int, button: Button)
    }
}