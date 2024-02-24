package com.example.settleaccounts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.persistableBundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.settleaccounts.databinding.ViewHolderPeopleBinding
import com.example.settleaccounts.model.People
import com.example.settleaccounts.view_model.PeopleEditViewModel

class PeopleEditAdapter(peopleLiveData: MutableLiveData<List<EditText>>): RecyclerView.Adapter<PeopleEditAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ViewHolderPeopleBinding): RecyclerView.ViewHolder(binding.root) {

        fun getBind() : ViewHolderPeopleBinding {
            return binding
        }
    }

    private var peopleList = peopleLiveData.value?.toMutableList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewHolderPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        peopleList?.set(position, holder.getBind().peopleEditTextView)
    }


    companion object {
        var count = 0
    }
}