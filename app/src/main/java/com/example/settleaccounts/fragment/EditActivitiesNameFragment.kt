package com.example.settleaccounts.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.example.settleaccounts.R
import com.example.settleaccounts.databinding.FragmentEditActivitiesNameBinding
import com.example.settleaccounts.view_model.EditActivitiesNameViewModel
import com.example.settleaccounts.view_model.PeopleAndActivitiesDataViewModel
import com.example.settleaccounts.view_model.SetNumberOfActivitiesViewModel
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditActivitiesNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditActivitiesNameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentEditActivitiesNameBinding
    private val viewModel: EditActivitiesNameViewModel by activityViewModels()
    private val editActivitiesNameViewModel: SetNumberOfActivitiesViewModel by activityViewModels()
    private val activitiesDataViewModel: PeopleAndActivitiesDataViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditActivitiesNameBinding.inflate(inflater, container, false)

        viewModel.setEditTextVisibility(editActivitiesNameViewModel.numberOfActivities.value!!)

        binding.viewModel = viewModel
        binding.fragment = this

        return binding.root
    }

    private fun setEditTextList(): List<TextInputEditText> {

        return listOf(
            binding.peopleEditTextView1, binding.peopleEditTextView2,
            binding.peopleEditTextView3, binding.peopleEditTextView4,
            binding.peopleEditTextView5, binding.peopleEditTextView6,
            binding.peopleEditTextView7, binding.peopleEditTextView8,
            binding.peopleEditTextView9, binding.peopleEditTextView10,
        )
    }

    fun goToNextPage() {
        activitiesDataViewModel.clearTempActivityList()
        val editTextList = setEditTextList()
        val count = editActivitiesNameViewModel.numberOfActivities.value
        for(i in 0..< count!!) {

            if(editTextList[i].text.toString() == "") {
                Toast.makeText(context, "활동명을 모두 입력해주세요", Toast.LENGTH_SHORT).show()
                activitiesDataViewModel.clearTempActivityList()
                return
            }

            activitiesDataViewModel.addActivity(editTextList[i].text.toString())
        }
        activitiesDataViewModel.apply {
            confirmActivitiesMap()
        }

        activity?.supportFragmentManager?.commit {
            replace(R.id.settle_frame_layout, InputAndPickFragment())
            addToBackStack("")
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditActivitiesNameFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditActivitiesNameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}