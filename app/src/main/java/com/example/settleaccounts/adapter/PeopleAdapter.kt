package com.example.settleaccounts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.settleaccounts.databinding.ViewHolderPeopleBinding
import com.example.settleaccounts.dialog.AllSelect
import com.example.settleaccounts.model.Activity

class PeopleAdapter(
    private val peopleLiveData: MutableLiveData<HashMap<String, Double>>,
    val activity: Activity,
    val peopleSelectedMap: HashMap<String, Boolean>,
): RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private val peopleMap: HashMap<String, Double> = peopleLiveData.value!!
    private val peopleList: List<Pair<String, Double>> = peopleMap.toList()
    private var isSelectedArray = Array<Boolean>(peopleMap.size) {false}

    private val tempSelectedPeopleList: ArrayList<String> = arrayListOf()

    lateinit var binding: ViewHolderPeopleBinding

    fun setSelectedActivity(): MutableList<String> {

        //activity.peopleList.clear()
        //activity.peopleList = tempSelectedPeopleList.clone() as MutableList<String>
        val tempList: MutableList<String> = mutableListOf()
        for(i in isSelectedArray.indices) {

            if(isSelectedArray[i]) {
                tempList.add(peopleList[i].first)
            }
        }
        return tempList

    }

    fun setAllSelect() {
        isSelectedArray = Array<Boolean>(peopleMap.size) {true}

    }

    inner class ViewHolder(binding: ViewHolderPeopleBinding, private val viewType: Int): RecyclerView.ViewHolder(binding.root) {

        val checkBox: CheckBox = binding.personCheckBox

        init {
            setItem(viewType)
            setItemChecked()
        }

        fun setItem(position: Int) {

            binding.personCheckBox.setOnCheckedChangeListener{buttonView, isChecked ->
                run {
                    isSelectedArray[viewType] = isChecked
                    val name = buttonView.text.toString()

                    tempSelectedPeopleList.remove(name)
                    if(isChecked) {
                        tempSelectedPeopleList.add(name)
                    }
                }
            }
            checkBox.text = peopleList[viewType].first
        }

        fun setItemChecked() {

            if(activity.peopleList.contains(checkBox.text.toString())) {
                checkBox.isChecked = activity.peopleList.contains(checkBox.text.toString())
                isSelectedArray[viewType] = checkBox.isChecked
            }

        }

        fun setAllChecked() {
            checkBox.isChecked = isSelectedArray[viewType]
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