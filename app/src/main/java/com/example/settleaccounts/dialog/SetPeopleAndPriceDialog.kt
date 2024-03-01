package com.example.settleaccounts.dialog

import android.app.Dialog
import android.view.Window
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.settleaccounts.adapter.ActivityAdapter
import com.example.settleaccounts.adapter.PeopleAdapter
import com.example.settleaccounts.databinding.DialogSetPeopleAndPriceBinding
import com.example.settleaccounts.fragment.InputAndPickFragment
import com.example.settleaccounts.model.Activity

class SetPeopleAndPriceDialog(
    private val peopleLiveData: MutableLiveData<HashMap<String, Double>>,
    private val selectedActivity: Activity,
    private val fragment: InputAndPickFragment,
    private val viewHolder: ActivityAdapter.ViewHolder,
) {


    private lateinit var binding: DialogSetPeopleAndPriceBinding
    private val dlg = Dialog(fragment.requireContext())

    private fun setSelectedActivity() {

        selectedActivity.money = binding.priceEditText.text.toString().toInt()
        viewHolder.setItemImage()
    }
    fun show() {
        binding = DialogSetPeopleAndPriceBinding.inflate(fragment.layoutInflater)
        val peopleAdapter = PeopleAdapter(peopleLiveData, selectedActivity)
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(binding.root)

        binding.peopleRecyclerView.apply {
            adapter = peopleAdapter
            layoutManager = GridLayoutManager(fragment.requireContext(), 3)
        }

        binding.setCompleteButton.setOnClickListener {
            peopleAdapter.setSelectedActivity()
            setSelectedActivity()
        }

        dlg.show()
    }
}