package com.example.a18.ui.listfragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a18.Status
import com.example.a18.data.retrofitrequest.RequestModel
import com.example.a18.databinding.FragmentListBinding
import dagger.android.support.DaggerFragment

class ListFragment : Fragment() {
    private val component by lazy { ListFragmentComponent.create() }

    private var _binding:FragmentListBinding? = null
    private val binding get() = _binding!!

    private val adapter by lazy{ RecViewAdapter() }

    private val viewModel : MyViewModel by viewModels{component.viewModelFactory()}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recView = binding.recView
        val layoutManager = LinearLayoutManager(requireContext())

        recView.layoutManager = layoutManager
        adapter.allData = emptyList()
        recView.adapter = adapter

        viewModel.getRequest().observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recView.visibility = View.VISIBLE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        recView.visibility = View.VISIBLE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        recView.visibility = View.GONE
                    }
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun retrieveList(requests: List<RequestModel>) {
        adapter.apply {
            setRequestsData(requests)
        }
    }

}