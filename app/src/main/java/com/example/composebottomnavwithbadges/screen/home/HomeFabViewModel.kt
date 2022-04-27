package com.example.composebottomnavwithbadges.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel

class HomeFabViewModel: ViewModel() {
    init{
        Log.v("Compose","Init HomeFabViewModel...")
    }

    override fun onCleared() {
        Log.v("Compose","onCleared HomeFabViewModel...")
        super.onCleared()
    }
}