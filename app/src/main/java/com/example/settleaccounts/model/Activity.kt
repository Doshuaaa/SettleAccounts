package com.example.settleaccounts.model

data class Activity (
    val name: String,
    var money: Int,
    var peopleList: MutableList<String>
)