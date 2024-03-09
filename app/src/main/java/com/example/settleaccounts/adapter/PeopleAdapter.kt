package com.example.settleaccounts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.settleaccounts.databinding.ViewHolderPeopleBinding
import com.example.settleaccounts.model.Activity

class PeopleAdapter(
    peopleLiveData: MutableLiveData<HashMap<String, Double>>,
    val activity: Activity,
): RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private val peopleList: List<Pair<String, Double>> = peopleLiveData.value!!.toList()
    private var isSelectedArray = Array(peopleList.size) {false}

    lateinit var binding: ViewHolderPeopleBinding

    fun setSelectedActivity(): MutableList<String> {

        val tempList: MutableList<String> = mutableListOf()
        for(i in isSelectedArray.indices) {

            if(isSelectedArray[i]) {
                tempList.add(peopleList[i].first)
            }
        }

        return tempList
    }

    fun setAllSelect() {
        isSelectedArray.fill(true)
    }

    inner class ViewHolder(binding: ViewHolderPeopleBinding, private val viewType: Int): RecyclerView.ViewHolder(binding.root) {

        val checkBox: CheckBox = binding.personCheckBox

        init {
            setItem()
            setItemChecked()
        }

        private fun setItem() {

            binding.personCheckBox.setOnCheckedChangeListener{buttonView, isChecked ->

                isSelectedArray[viewType] = isChecked
            }
            checkBox.text = peopleList[viewType].first
        }

        private fun setItemChecked() {

            if(activity.peopleList.contains(checkBox.text.toString())) {
                checkBox.isChecked = activity.peopleList.contains(checkBox.text.toString())
                isSelectedArray[viewType] = checkBox.isChecked
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ViewHolderPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, viewType)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {

            checkBox.isChecked = isSelectedArray[position]
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}