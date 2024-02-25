package com.example.settleaccounts.view_model

import android.view.View
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditPeopleNameViewModel : ViewModel() {
    private val _peopleList = MutableLiveData<List<EditText>>()
    private val list = mutableListOf<EditText>()

    private val _visibleList = MutableLiveData<List<Int>>()

    val visibleList: LiveData<List<Int>> get() = _visibleList
    val peopleList get() = _peopleList


    fun setEditTextVisibility(visibleCount: Int) {
        val list = MutableList(20) { View.GONE }
        for(i in 0..< visibleCount)  {
            list[i] = View.VISIBLE
        }
        _visibleList.value = list
    }
}