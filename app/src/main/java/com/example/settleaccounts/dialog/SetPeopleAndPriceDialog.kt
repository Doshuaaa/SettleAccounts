package com.example.settleaccounts.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
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
    private lateinit var peopleAdapter: PeopleAdapter
    private val dlg = Dialog(fragment.requireContext())

    private fun setSelectedActivity() {
            selectedActivity.money = binding.priceEditText.text.toString().toInt()
            viewHolder.setItemImage()
    }
    fun show() {
        binding = DialogSetPeopleAndPriceBinding.inflate(fragment.layoutInflater)
        binding.dialog = this
        peopleAdapter = PeopleAdapter(peopleLiveData, selectedActivity)
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(binding.root)
        val windowManager = fragment.context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val rect = windowManager.currentWindowMetrics.bounds
        val x = (rect.width() * 0.8f).toInt()
        val y = (rect.height() * 0.5f).toInt()
        dlg.window?.setLayout(x, y)
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.peopleRecyclerView.apply {
            adapter = peopleAdapter
            layoutManager = GridLayoutManager(fragment.requireContext(), 3)
        }

        dlg.show()
    }

    fun completeSet() {
        if(binding.priceEditText.text.toString() != "") {
            peopleAdapter.setSelectedActivity()
            setSelectedActivity()
            dlg.dismiss()
        }
        else {
            Toast.makeText(fragment.context, "정산할 금액을 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }
}