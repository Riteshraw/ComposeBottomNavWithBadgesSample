package com.example.composebottomnavwithbadges.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SettingViewModel: ViewModel() {
    var valResponse:Int by mutableStateOf(0)
    var listResponse:List<String> by mutableStateOf(listOf())

    fun updateVal(){
        valResponse += 1
    }

    fun getList(){
        listResponse = listOf(
            "Android 20",
            "Android 21",
            "Android 22",
            "Android 23",
            "Android 24",
            "Android 25",
            "Android 26",
            "Android 27",
            "Android 28",
            "Android 29",
            "Android 30",
            "Android 30",
            "Android 31",
            "Android 32",
            "Android 33",
            "Android 34",
            "Android 35",
            "Android 36",
            "Android 37",
            "Android 38",
            "Android 39",
            "Android 40"
        )
    }
}