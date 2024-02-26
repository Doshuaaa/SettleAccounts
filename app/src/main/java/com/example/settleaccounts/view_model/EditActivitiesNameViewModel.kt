package com.example.settleaccounts.view_model

import android.view.View
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditActivitiesNameViewModel : ViewModel() {
    private val list = mutableListOf<EditText>()

    private val _visibleList = MutableLiveData<List<Int>>()

    val visibleList: LiveData<List<Int>> get() = _visibleList


    fun setEditTextVisibility(visibleCount: Int) {
        val list = MutableList(10) { View.GONE }
        for(i in 0..< visibleCount)  {
            list[i] = View.VISIBLE
        }
        _visibleList.value = list
    }
}