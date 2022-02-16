package com.example.composebottomnavwithbadges.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    var valResponse:Int by mutableStateOf(0)

    fun updateVal(){
        valResponse += 1
    }
}