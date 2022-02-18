package com.example.composebottomnavwithbadges.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.composebottomnavwithbadges.R

sealed class MenuAction(
    @StringRes val label: Int,
    @DrawableRes val icon: Int) {

    object Contact : MenuAction(R.string.menu_item_contact, R.mipmap.icon_contact_48)
}