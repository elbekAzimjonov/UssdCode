package com.elbek.ussdcode.models

data class Ussd(
    var ussdNumber: String,
    var ussd_name: String,
    var ussdAbout: String,
    var ussdCode: String,
    var btnVisibility: Boolean = false
)