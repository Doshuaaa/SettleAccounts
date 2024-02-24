package com.example.settleaccounts.view_model

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SetNumberOfPeopleViewModel : ViewModel() {

    private val _numberOfPeople = MutableLiveData<Int>()
    private val _toastMessage = MutableLiveData<String>()
    private val _calFlag = MutableLiveData<String>()

    val numberOfPeople: LiveData<Int> get() = _numberOfPeople
    val toastMessage: LiveData<String> get() =  _toastMessage
    val calFlag: LiveData<String> get() = _calFlag

    init {

        _numberOfPeople.value = 0
        _calFlag.value = "zero"
    }

    fun addPeople() {

        if(_numberOfPeople.value!! < 20) {
            _calFlag.value = "add"
            _numberOfPeople.value = _numberOfPeople.value?.plus(1)
        }
        else {
            _toastMessage.value = "인원 수는 20명까지 입력 가능해요"
        }
    }

    fun subtractPeople() {

        if(_numberOfPeople.value!! > 0) {
            _calFlag.value = "subtract"
            _numberOfPeople.value = _numberOfPeople.value?.minus(1)
        }
        else {
            _toastMessage.value = "인원 수를 0명 이상 입력 해주세요"
        }
    }
}