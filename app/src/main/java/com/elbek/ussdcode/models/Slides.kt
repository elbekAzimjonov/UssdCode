package com.elbek.ussdcode.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Slides(
    val tarifNomi:String,
    val tarifNarxi:String,
    val tarifTimeImg:Int,
    val tarifSmsImg:Int,
    val tarifMbImg:Int,
    val time_title:String,
    val sms_title:String,
    val mb_title:String,
    val tarif_abaut:String,
    val tarif_code:String,
    val tarif_link:String
):Parcelable