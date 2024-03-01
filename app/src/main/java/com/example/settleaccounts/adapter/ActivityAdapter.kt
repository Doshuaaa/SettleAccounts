package com.example.settleaccounts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.settleaccounts.R
import com.example.settleaccounts.databinding.ViewHolderActivityBinding
import com.example.settleaccounts.dialog.SetPeopleAndPriceDialog
import com.example.settleaccounts.fragment.InputAndPickFragment
import com.example.settleaccounts.model.Activity

class ActivityAdapter(
    activityLiveData: MutableLiveData<List<Activity>>,
    private val peopleLiveData: MutableLiveData<HashMap<String, Double>>,
    private val fragment: InputAndPickFragment)
    : RecyclerView.Adapter<ActivityAdapter.ViewHolder>()  {


    val activityList = activityLiveData.value!!

    inner class ViewHolder(val binding: ViewHolderActivityBinding): RecyclerView.ViewHolder(binding.root) {

        private var num = 0

        fun setActivityItem(position: Int) {
            num = position + 1
            binding.activityNumTextView.text = num.toString()
            binding.activityNameTextView.text = activityList[position].name

            setItemImage()

            binding.activityLinearLayout.setOnClickListener {
                SetPeopleAndPriceDialog(peopleLiveData, activityList[position], fragment, this).show()
            }
        }

        fun setItemImage() {
            if(activityList[num - 1].money == 0) {
                binding.setCompleteImageView.setImageResource(R.drawable.not_yet)
            } else {
                binding.setCompleteImageView.setImageResource(R.drawable.complete)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewHolderActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return activityList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setActivityItem(position)
    }
}