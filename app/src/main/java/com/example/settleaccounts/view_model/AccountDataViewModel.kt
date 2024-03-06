package com.example.settleaccounts.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.settleaccounts.model.Account

class AccountDataViewModel: ViewModel() {

    private val _account =  MutableLiveData<Account>()

    val account: LiveData<Account> get() = _account

    fun saveAccount(bankName: String, accountNumber: String) {

        _account.value = Account(bankName, accountNumber)
    }
    fun getMyAccount() : Account{
        return _account.value!!
    }
}