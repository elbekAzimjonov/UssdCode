package com.elbek.ussdcode.models

data class Sms(
    var smsNumber: Int,
    var sms_name: String,
    var smsAbout: String,
    var smsCode: String,
    var btnVisibility: Boolean = false
)