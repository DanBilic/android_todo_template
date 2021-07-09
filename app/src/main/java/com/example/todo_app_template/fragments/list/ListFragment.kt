package com.example.todo_app_template.fragments.list

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_app_template.R
import com.example.todo_app_template.data.viewmodel.ToDoViewModel
import com.example.todo_app_template.databinding.FragmentListBinding
import com.example.todo_app_template.fragments.SharedViewModel


class ListFragment : Fragment() {

    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private val adapter: ListAdapter by lazy {ListAdapter()}

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // data binding
        _binding = FragmentListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel

        // observe lifedata
        mToDoViewModel.getALlData.observe(viewLifecycleOwner, Observer {data ->
            adapter.setData(data)
        })

        // setup recyclerview
        setUpRecyclerView()

        // set menu
       setHasOptionsMenu(true)


        return binding.root
    }

    private fun setUpRecyclerView() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // set binding o null to avoid memory leaks when view is destroyed
        _binding = null
    }


}