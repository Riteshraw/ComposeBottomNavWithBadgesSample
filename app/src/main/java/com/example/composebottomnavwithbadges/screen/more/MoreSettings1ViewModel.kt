package com.example.composebottomnavwithbadges.screen.more

import android.util.Log
import androidx.lifecycle.ViewModel

class MoreSettings1ViewModel: ViewModel() {
    init{
        Log.v("Compose","Init MoreSettings1ViewModel...")
    }

    override fun onCleared() {
        Log.v("Compose","onCleared MoreSettings1ViewModel...")
        super.onCleared()
    }
}