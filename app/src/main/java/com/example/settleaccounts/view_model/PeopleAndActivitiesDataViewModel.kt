package com.example.settleaccounts.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.settleaccounts.model.Activity

open class PeopleAndActivitiesDataViewModel: ViewModel() {

    private val _peopleMap = MutableLiveData<HashMap<String, Double>>()
    private val _activityList = MutableLiveData<List<Activity>>()


    val peopleMap get() = _peopleMap
    val activityList get() = _activityList


    private val tempPeopleMap: HashMap<String, Double> = hashMapOf()
    private val tempActivityList: ArrayList<Activity> = arrayListOf()

    fun addPerson(name: String): Boolean {
        if(!tempPeopleMap.contains(name)) {
            tempPeopleMap[name] = 0.0
        }
        else {
            clearTempPeopleMap()
            return false
        }
        return true
    }

    fun confirmPeopleMap() {
        _peopleMap.value = HashMap<String, Double>().apply { putAll(tempPeopleMap) }
        tempPeopleMap.clear()
    }

    private fun clearTempPeopleMap() {
        tempPeopleMap.clear()
    }

    fun addActivity(name: String) {
        tempActivityList.add(Activity(name, 0, mutableListOf()))
    }

    fun confirmActivitiesMap() {
        _activityList.value = tempActivityList.clone() as ArrayList<Activity>
        tempActivityList.clear()
    }

    fun settleAccounts() {

        val activityList = _activityList.value!!
        val peopleCount = peopleMap.value!!.size

        for(list in activityList) {

            val money = list.money / peopleCount

            for(peopleName in list.peopleList) {
                val map = peopleMap.value!!
                map[peopleName] = money + map[peopleName]!!
            }
        }
    }
}