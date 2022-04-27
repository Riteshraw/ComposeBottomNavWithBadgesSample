package com.example.composebottomnavwithbadges.dao

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: Long = 0,
    var name: String = ""
) : Parcelable