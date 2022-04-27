package com.example.composebottomnavwithbadges.screen.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BenefitViewModel : ViewModel() {
    var valResponse: Int by mutableStateOf(0)
    var listResponse: List<String> by mutableStateOf(listOf())

    //lateinit var listState: LazyListState
    var firstItemIndex: Int? = null
    var firstItemScrollOffset: Int? = null

    init {
        getList()
    }

    fun updateVal() {
        valResponse += 1
    }

    /*fun isListStateInitialised(): Boolean {
        return this::firstItemIndex.isNot
    }*/

    fun saveState(firstVisibleItemIndex: Int, firstVisibleItemScrollOffset: Int) {
        //this.listState = LazyListState(firstVisibleItemIndex, firstVisibleItemScrollOffset)
        firstItemIndex = firstVisibleItemIndex
        firstItemScrollOffset = firstVisibleItemScrollOffset
    }

    fun getList() {
        Log.i("Compose", "gettinglist BenefitViewModel")

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

    override fun onCleared() {
        Log.v("Compose","onCleared BenefitViewModel...")
        super.onCleared()
    }
}