package com.example.recipi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _notifications = MutableLiveData<MutableList<String>>(mutableListOf())
    val notifications: LiveData<MutableList<String>> = _notifications

    fun addNotification(notification: String) {
        _notifications.value?.apply {
            add(notification)
            _notifications.value = this // Notify observers
        }
    }
}
