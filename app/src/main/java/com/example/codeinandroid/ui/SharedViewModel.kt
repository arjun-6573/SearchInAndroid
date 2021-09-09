package com.example.codeinandroid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.codeinandroid.utils.SingleLiveEvent

class SharedViewModel : ViewModel() {
    private val _refreshEvents = SingleLiveEvent<Boolean>()
    val refreshEvents: LiveData<Boolean> = _refreshEvents

    fun onFavouriteStatusChange() {
        _refreshEvents.value = true
    }
}