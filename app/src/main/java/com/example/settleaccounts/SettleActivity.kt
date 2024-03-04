package com.example.settleaccounts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.example.settleaccounts.databinding.ActivitySettleBinding
import com.example.settleaccounts.fragment.EditActivitiesNameFragment
import com.example.settleaccounts.fragment.EditPeopleNameFragment
import com.example.settleaccounts.fragment.InputAccountNumberFragment
import com.example.settleaccounts.fragment.InputAndPickFragment
import com.example.settleaccounts.fragment.ResultOfSettleAccountsFragment
import com.example.settleaccounts.fragment.SetNumberOfActivitiesFragment
import com.example.settleaccounts.fragment.SetNumberOfPeopleFragment

const val FRAGMENT_ACTION = "fragment_action"

const val SET_NUMBER_OF_PEOPLE = "set_number_of_people"
const val SET_NUMBER_OF_ACTIVITY = "set_number_of_activity"
const val RESULT_OF_SETTLE_ACCOUNTS = "result_of_settle_accounts"
const val EDIT_PEOPLE_NAME = "edit_people_name"
const val EDIT_ACTIVITIES_NAME = "edit_activities_name"
const val INPUT_ACCOUNT_NUMBER = "input_account_number"
const val INPUT_AND_PICK = "input_and_pick"

class SettleActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySettleBinding

    private val fragmentList = listOf(
        SET_NUMBER_OF_ACTIVITY, EDIT_PEOPLE_NAME,
        SET_NUMBER_OF_PEOPLE, EDIT_ACTIVITIES_NAME,)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settle)
        supportFragmentManager.commit {
            replace(R.id.settle_frame_layout, SetNumberOfPeopleFragment())
        }
    }

    inner class FragmentReceiver: BroadcastReceiver() {
        override fun onReceive(p0: Context?, intent: Intent) {

            supportFragmentManager.commit {

                when(intent.action) {

                    SET_NUMBER_OF_PEOPLE -> replace(R.id.settle_frame_layout, SetNumberOfPeopleFragment())
                    SET_NUMBER_OF_ACTIVITY -> replace(R.id.settle_frame_layout, SetNumberOfActivitiesFragment())
                    EDIT_PEOPLE_NAME -> replace(R.id.settle_frame_layout, EditPeopleNameFragment())
                    EDIT_ACTIVITIES_NAME -> replace(R.id.settle_frame_layout, EditActivitiesNameFragment())
                    INPUT_ACCOUNT_NUMBER -> replace(R.id.settle_frame_layout, InputAccountNumberFragment())
                    INPUT_AND_PICK -> replace(R.id.settle_frame_layout, InputAndPickFragment())
                    RESULT_OF_SETTLE_ACCOUNTS -> replace(R.id.settle_frame_layout, ResultOfSettleAccountsFragment())
                }
            }
        }

    }

}