package com.example.settleaccounts.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.settleaccounts.model.People

class SetNumberOfPeopleViewModel : ViewModel() {

    val numberOfPeople = MutableLiveData<Int>()
//    private val _peopleListLiveData = MutableLiveData<List<People>>()
//
//    val peopleListLiveData : LiveData<List<People>> get() = _peopleListLiveData
//
//    val peopleList = mutableListOf<People>()

    init {
        numberOfPeople.value = 0
    }

    fun addPeople() {

        if(numberOfPeople.value!! < 10) {
            numberOfPeople.value = numberOfPeople.value?.plus(1)

        }
        else {
           //toast
        }
    }

    fun subtractPeople() {

        if(numberOfPeople.value!! > 0) {
            numberOfPeople.value = numberOfPeople.value?.minus(1)

        }
        else {
            //toast
        }
    }
}