package com.example.settleaccounts.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.example.settleaccounts.R
import com.example.settleaccounts.databinding.FragmentInputAccountNumberBinding
import com.example.settleaccounts.view_model.AccountDataViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InputAccountNumberFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputAccountNumberFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentInputAccountNumberBinding
    private val viewModel: AccountDataViewModel by activityViewModels()

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

        binding = FragmentInputAccountNumberBinding.inflate(inflater, container, false)

        binding.apply {
            accountSetCompleteButton.setOnClickListener {

                viewModel.saveAccount(bankNameTextView.text.toString(), accountNumberTextView.text.toString())
                goToNextPage()
            }
        }

        return binding.root
    }

    fun goToNextPage() {
        activity?.supportFragmentManager?.commit {
            replace(R.id.settle_frame_layout, ResultOfSettleAccountsFragment())
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
         * @return A new instance of fragment InputAccountNumberFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InputAccountNumberFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}