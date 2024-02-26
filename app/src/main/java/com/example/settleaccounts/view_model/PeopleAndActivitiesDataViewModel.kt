package com.example.settleaccounts.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PeopleAndActivitiesDataViewModel: ViewModel() {

    private val _peopleMap = MutableLiveData<HashMap<String, Int>>()

    private val _activitiesMap = MutableLiveData<HashMap<String, Int>>()

    val peopleMap get() = _peopleMap
    val activitiesMap get() = _activitiesMap

    private val tempPeopleMap: HashMap<String, Int> = hashMapOf()
    private val tempActivitiesMap: HashMap<String, Int> = hashMapOf()

    fun addPerson(name: String): Boolean {
        if(!tempPeopleMap.contains(name)) {
            tempPeopleMap[name] = 0
        }
        else {
            clearTempPeopleMap()
            return false
        }
        return true
    }

    fun confirmPeopleMap() {
        _peopleMap.value = tempPeopleMap
        tempPeopleMap.clear()
    }

    private fun clearTempPeopleMap() {
        tempPeopleMap.clear()
    }

    fun addActivity(name: String): Boolean {
        if(!tempActivitiesMap.contains(name)) {
            tempActivitiesMap[name] = 0
        }
        else {
            clearTempActivitiesMap()
            return false
        }
        return true
    }

    fun confirmActivitiesMap() {
        _activitiesMap.value = tempActivitiesMap
        tempActivitiesMap.clear()
    }

    private fun clearTempActivitiesMap() {
        tempActivitiesMap.clear()
    }
}