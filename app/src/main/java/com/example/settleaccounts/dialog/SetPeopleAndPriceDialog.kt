package com.example.settleaccounts.dialog

import android.annotation.SuppressLint
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
import com.example.settleaccounts.view_model.PersonIsCheckedMap

object AllSelect {
    var flag = false
}

class SetPeopleAndPriceDialog(
    private val peopleLiveData: MutableLiveData<HashMap<String, Double>>,
    private val personIsCheckedMap: HashMap<String, PersonIsCheckedMap>,
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

    @SuppressLint("NotifyDataSetChanged")
    fun show() {
        binding = DialogSetPeopleAndPriceBinding.inflate(fragment.layoutInflater)
        binding.dialog = this
        peopleAdapter = PeopleAdapter(peopleLiveData, selectedActivity, personIsCheckedMap[selectedActivity.name]?.map!!)
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

        binding.selectAllCheckBox.setOnClickListener {
            //AllSelect.flag = true
            peopleAdapter.setAllSelect()
            peopleAdapter.notifyDataSetChanged()
//            binding.peopleRecyclerView.post(Runnable {
//                AllSelect.flag = false
//            })
        }

//        binding.selectAllCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
//
//            val isCheckedMap = personIsCheckedMap[selectedActivity.name]!!
//            val list = isCheckedMap.map.toList()
//
//            for( person in list) {
//
//                isCheckedMap.map[person.first] = isChecked
//            }
//
//            peopleAdapter.notifyDataSetChanged()
//        }
        dlg.show()
    }

    fun completeSet() {



        if(binding.priceEditText.text.toString() != "" ) {

            val list = peopleAdapter.setSelectedActivity()
            if(list.size == 0) {
                Toast.makeText(fragment.context, "인원을 1명 이상 선택해 주세요", Toast.LENGTH_SHORT).show()
                return
            }
            selectedActivity.peopleList = list
            setSelectedActivity()

            dlg.dismiss()
        }
        else {
            Toast.makeText(fragment.context, "정산할 금액을 입력해주세요", Toast.LENGTH_SHORT).show()
        }
    }
}