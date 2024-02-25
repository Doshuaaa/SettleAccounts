package com.example.settleaccounts.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.settleaccounts.model.Person

class PeopleDataViewModel: ViewModel() {

    private val _peopleList = MutableLiveData<List<Person>>()

    val peopleList get() = _peopleList
    private val tempList: MutableList<Person> = mutableListOf()

    fun addPerson(name: String) {
        tempList.add(Person(name, 0))
    }

    fun confirmPeopleList() {
        _peopleList.value = tempList
    }
}