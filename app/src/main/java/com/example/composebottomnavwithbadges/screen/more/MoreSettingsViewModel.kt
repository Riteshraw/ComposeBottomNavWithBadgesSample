package com.example.composebottomnavwithbadges.screen.more

import android.util.Log
import androidx.lifecycle.ViewModel

class MoreSettingsViewModel: ViewModel() {
    init{
        Log.v("Compose","Init MoreSettingsViewModel...")
    }

    override fun onCleared() {
        Log.v("Compose","onCleared MoreSettingsViewModel...")
        super.onCleared()
    }
}