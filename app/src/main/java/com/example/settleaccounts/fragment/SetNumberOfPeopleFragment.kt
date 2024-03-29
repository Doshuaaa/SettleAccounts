package com.example.settleaccounts.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.example.settleaccounts.R
import com.example.settleaccounts.databinding.FragmentSetNumberOfPeopleBinding
import com.example.settleaccounts.view_model.SetNumberOfPeopleViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SetNumberOfPeopleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SetNumberOfPeopleFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentSetNumberOfPeopleBinding
    private val viewModel: SetNumberOfPeopleViewModel by activityViewModels()

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

        binding = FragmentSetNumberOfPeopleBinding.inflate(inflater, container, false)

        var toast: Toast? = null

        viewModel.toastMessage.observe(viewLifecycleOwner, Observer { message ->

            toast?.cancel()
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            toast?.show()
        })

        binding.setNumberOfPeopleFragmentViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.activity = this

        return binding.root
    }

    fun goToNextPage() {

        if(viewModel.numberOfPeople.value != 0) {
            activity?.supportFragmentManager?.commit {
                viewModel.setInitMessage()
                replace(R.id.settle_frame_layout, EditPeopleNameFragment())
                addToBackStack("")
            }
        }
        else {
            Toast.makeText(context, "인원 수가 0명일 수는 없어요", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SetNumberOfPeopleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SetNumberOfPeopleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}