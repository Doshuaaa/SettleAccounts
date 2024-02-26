package com.example.settleaccounts.view_model

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SetNumberOfPeopleViewModel : ViewModel() {

    private val _numberOfPeople = MutableLiveData<Int>()
    private var _toastMessage = MutableLiveData<String>()

    val numberOfPeople: LiveData<Int> get() = _numberOfPeople
    val toastMessage: LiveData<String> get() =  _toastMessage

    init {

        _numberOfPeople.value = 0
    }

    fun addPeople() {

        if(_numberOfPeople.value!! < 20) {
            _numberOfPeople.value = _numberOfPeople.value?.plus(1)
        }
        else {
            _toastMessage.value = "인원 수는 20명까지 입력 가능해요"
        }
    }

    fun subtractPeople() {

        if(_numberOfPeople.value!! > 0) {
            _numberOfPeople.value = _numberOfPeople.value?.minus(1)
        }
        else {
            _toastMessage.value = "인원 수를 0명 이상 입력 해주세요"
        }
    }

    fun setInitMessage() {
        _toastMessage = MutableLiveData<String>()
    }
}