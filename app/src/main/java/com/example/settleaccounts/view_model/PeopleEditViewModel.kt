package com.example.settleaccounts.view_model

import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.settleaccounts.model.People

class PeopleEditViewModel : ViewModel() {
    private val _peopleList = MutableLiveData<List<EditText>>()
    private val list = mutableListOf<EditText>()

    val peopleList get() = _peopleList


}