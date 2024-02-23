package com.example.settleaccounts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.example.settleaccounts.databinding.ActivitySettleBinding
import com.example.settleaccounts.fragment.SetNumberOfPeopleFragment

class SettleActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySettleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settle)
        supportFragmentManager.commit {
            replace(R.id.settle_frame_layout, SetNumberOfPeopleFragment())
        }
    }
}