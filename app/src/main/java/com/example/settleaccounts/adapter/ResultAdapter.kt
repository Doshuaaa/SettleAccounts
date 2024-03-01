package com.example.settleaccounts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.settleaccounts.databinding.ViewHolderResultOfPeopleBinding

class ResultAdapter(private  val peopleList: List<Pair<String, Double>>) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ViewHolderResultOfPeopleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setView(position: Int) {
            val num = position + 1
            binding.apply {
                numberTextView.text = num.toString()
                personNameTextView.text = peopleList[position].first
                priceTextView.text = peopleList[position].second.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewHolderResultOfPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setView(position)
    }
}