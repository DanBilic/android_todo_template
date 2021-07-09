package com.example.todo_app_template.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
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
        // set so ViewModel livedata can be observed from layout
        binding.lifecycleOwner = this

        // set mSharedViewModel as binding variable to layout
        // layout has access to mSharedViewModel
        binding.mSharedViewModel = mSharedViewModel


        // setup recyclerview
        setUpRecyclerView()


        // observe life data
        mToDoViewModel.getALlData.observe(viewLifecycleOwner, Observer {data ->
            mSharedViewModel.checkIfDatabaseEmpty(data)
            adapter.setData(data)
        })


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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete_all){
            confirmRemoval()
        }
        return super.onOptionsItemSelected(item)
    }

    // show dialog to confirm removal of all items from database
    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mToDoViewModel.deleteAll()
            Toast.makeText(
                requireContext(),
                "Successfully removed all entries !",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No"){_, _ ->}
        builder.setTitle("Delete all entries ?")
        builder.setMessage("Are you sure you want to remove all entries ?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // set binding o null to avoid memory leaks when view is destroyed
        _binding = null
    }


}