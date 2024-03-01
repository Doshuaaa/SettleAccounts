package com.example.settleaccounts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.settleaccounts.databinding.ViewHolderPeopleBinding
import com.example.settleaccounts.model.Activity

class PeopleAdapter(
    private val peopleLiveData: MutableLiveData<HashMap<String, Double>>,
    val activity: Activity,
): RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private val peopleMap: HashMap<String, Double> = peopleLiveData.value!!
    private val peopleList: List<Pair<String, Double>> = peopleMap.toList()

    private val tempSelectedPeopleList: ArrayList<String> = arrayListOf()

    lateinit var binding: ViewHolderPeopleBinding

    fun setSelectedActivity() {
        activity.peopleList = tempSelectedPeopleList.clone() as MutableList<String>
    }

    inner class ViewHolder(binding: ViewHolderPeopleBinding): RecyclerView.ViewHolder(binding.root) {

        fun setItem(position: Int) {
            binding.personCheckBox.text = peopleList[position].first

            binding.personCheckBox.setOnCheckedChangeListener{buttonView, isChecked ->
                run {
                    val name = buttonView.text.toString()
                    when(isChecked) {
                        true -> tempSelectedPeopleList.add(name)
                        false -> tempSelectedPeopleList.remove(name)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ViewHolderPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItem(position)
    }
}