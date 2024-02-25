package com.example.settleaccounts.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.settleaccounts.R
import com.example.settleaccounts.databinding.FragmentEditPeopleNameBinding
import com.example.settleaccounts.view_model.EditPeopleNameViewModel
import com.example.settleaccounts.view_model.PeopleDataViewModel
import com.example.settleaccounts.view_model.SetNumberOfPeopleViewModel
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditPeopleNameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditPeopleNameFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentEditPeopleNameBinding
    private val editNameViewModel: EditPeopleNameViewModel by activityViewModels()
    private val setNumberViewModel: SetNumberOfPeopleViewModel by activityViewModels()
    private val peopleDataViewModel: PeopleDataViewModel by activityViewModels()



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

        binding = FragmentEditPeopleNameBinding.inflate(inflater, container, false)
        binding.editPeopleNameViewModel = editNameViewModel

        editNameViewModel.setEditTextVisibility(setNumberViewModel.numberOfPeople.value!!)

        binding.nameSetCompleteButton.setOnClickListener {
            val editTextList = setEditTextList()
            val count = setNumberViewModel.numberOfPeople.value
            for(i in 0..< count!!) {
                peopleDataViewModel.addPerson(editTextList[i].text.toString())
            }
            peopleDataViewModel.confirmPeopleList()
        }

        return binding.root
    }

    private fun setEditTextList(): List<TextInputEditText> {

        return listOf(
            binding.peopleEditTextView1, binding.peopleEditTextView2,
            binding.peopleEditTextView3, binding.peopleEditTextView4,
            binding.peopleEditTextView5, binding.peopleEditTextView6,
            binding.peopleEditTextView7, binding.peopleEditTextView8,
            binding.peopleEditTextView9, binding.peopleEditTextView10,
            binding.peopleEditTextView11, binding.peopleEditTextView12,
            binding.peopleEditTextView13, binding.peopleEditTextView14,
            binding.peopleEditTextView15, binding.peopleEditTextView16,
            binding.peopleEditTextView17, binding.peopleEditTextView18,
            binding.peopleEditTextView19, binding.peopleEditTextView20,
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditPeopleNameFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditPeopleNameFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}