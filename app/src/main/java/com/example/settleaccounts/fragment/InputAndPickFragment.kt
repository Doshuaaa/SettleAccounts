package com.example.settleaccounts.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.settleaccounts.R
import com.example.settleaccounts.adapter.ActivityAdapter
import com.example.settleaccounts.databinding.FragmentInputAndPickBinding
import com.example.settleaccounts.view_model.PeopleAndActivitiesDataViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InputAndPickFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputAndPickFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var binding: FragmentInputAndPickBinding
    val viewModel: PeopleAndActivitiesDataViewModel by activityViewModels()

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

        val isCheckedMap = viewModel.personIsCheckedMap.value
        binding = FragmentInputAndPickBinding.inflate(layoutInflater, container, false)
        binding.fragment = this
        binding.activityRecyclerView.apply {
            adapter = ActivityAdapter(viewModel.activityList, viewModel.peopleMap,
                isCheckedMap, this@InputAndPickFragment)
            layoutManager = LinearLayoutManager(context)
        }

        return binding.root
    }

    fun goToNextPage() {
        viewModel.apply {
            initMoney()
            if(!settleAccounts()) {
                Toast.makeText(context, "정산 설정이 완료되지 않은 활동이 있어요", Toast.LENGTH_SHORT).show()
                return
            }
            //personIsCheckedMap.value = isCheckedMap
        }
        activity?.supportFragmentManager?.commit {
            replace(R.id.settle_frame_layout, InputAccountNumberFragment())
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
         * @return A new instance of fragment InputAndPickFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InputAndPickFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}