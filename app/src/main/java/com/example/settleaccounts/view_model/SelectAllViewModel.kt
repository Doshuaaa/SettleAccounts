package com.example.settleaccounts.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectAllViewModel : ViewModel() {

    private val _isCheckedSelectAll =  MutableLiveData<Boolean>()

    private val isCheckSelectAll get() = _isCheckedSelectAll

    fun setIsCheckedSelectAll(isChecked: Boolean) {

        _isCheckedSelectAll.value = isChecked
    }
}