package com.example.settleaccounts.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SetNumberOfActivitiesViewModel: ViewModel() {
    private val _numberOfActivities = MutableLiveData<Int>()
    private var _toastMessage = MutableLiveData<String>()

    val numberOfActivities: LiveData<Int> get() = _numberOfActivities
    val toastMessage: LiveData<String> get() =  _toastMessage

    init {

        _numberOfActivities.value = 0
    }

    fun addActivity() {

        if(_numberOfActivities.value!! < 10) {
            _numberOfActivities.value = _numberOfActivities.value?.plus(1)
        }
        else {
            _toastMessage.value = "활동 수는 10개까지 입력 가능해요"
        }
    }

    fun subtractActivity() {

        if(_numberOfActivities.value!! > 0) {
            _numberOfActivities.value = _numberOfActivities.value?.minus(1)
        }
        else {
            _toastMessage.value = "활동 수를 0개 이상 입력 해주세요"
        }
    }

    fun setInitMessage() {
        _toastMessage = MutableLiveData<String>()
    }
}