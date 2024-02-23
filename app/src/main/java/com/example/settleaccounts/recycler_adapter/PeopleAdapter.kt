package com.example.settleaccounts.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.settleaccounts.R
import com.example.settleaccounts.databinding.FragmentSetNumberOfPeopleBinding
import com.example.settleaccounts.model.People

class PeopleAdapter(private var peopleData: List<People>): RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    inner class ViewHolder(binding: FragmentSetNumberOfPeopleBinding)
        : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_people, parent, false)

        return ViewHolder(FragmentSetNumberOfPeopleBinding.bind(view))
    }

    override fun getItemCount(): Int {
        return peopleData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
}