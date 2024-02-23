package com.example.settleaccounts.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    private val _openCreateBuildingEvent = MutableLiveData<Unit>()
    val openCreateBuildingEvent: LiveData<Unit> get() = _openCreateBuildingEvent

    init {
        loadListBuildingDate()
    }

    private fun loadListBuildingDate() {

    }

    fun goToSettle() {

    }

}