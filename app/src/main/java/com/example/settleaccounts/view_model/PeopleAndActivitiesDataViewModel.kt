package com.example.settleaccounts.view_model

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.settleaccounts.model.Account
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

    fun clearTempPeopleMap() {
        tempPeopleMap.clear()
    }
    fun clearTempActivityList() {
        tempActivityList.clear()
    }

    fun addActivity(name: String) {
        tempActivityList.add(Activity(name, 0, mutableListOf()))
    }

    fun confirmActivitiesMap() {
        _activityList.value = tempActivityList.clone() as ArrayList<Activity>
        tempActivityList.clear()
    }

    fun settleAccounts() : Boolean {

        val activityList = _activityList.value!!

        for(list in activityList) {

            if(list.money != 0) {
                val money = list.money / list.peopleList.size

                for(peopleName in list.peopleList) {
                    val map = peopleMap.value!!
                    map[peopleName] = money + map[peopleName]!!
                }
            } else {
                return false
            }
        }
        return true
    }

    fun initMoney() {
        val map = _peopleMap.value!!

        for(name in map.keys) {
            map[name] = 0.0
        }
    }

    fun clipboard(context: Context, account: Account) {

        val clipboardManager: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val bank = account.bankName
        val accountNumber = account.accountNumber

        val str = StringBuilder()

        str.append(bank + '\n')
        str.append(accountNumber + '\n')
        str.append("위의 계좌로 입금을 부탁드려요" + "\n\n")

        for(person in _peopleMap.value?.toList()!!) {
            str.append("${person.first} - ${person.second}원\n")
        }

        str.append("\n")
        str.append("* 정산 상세 내용 * \n\n")

        for(activity in _activityList.value!!) {

            str.append("${activity.name} - ${activity.money}원\n")
            str.append("참여 인원 \n")
            for(people in activity.peopleList) {
                str.append("$people ")
            }
            str.append("\n\n")
        }

        str.delete(str.length - 2, str.length - 1)
        clipboardManager.setPrimaryClip(ClipData.newPlainText("label", str))
    }
}