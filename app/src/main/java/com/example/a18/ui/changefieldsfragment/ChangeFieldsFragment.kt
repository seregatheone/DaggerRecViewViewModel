package com.example.a18.ui.changefieldsfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.a18.R
import com.example.a18.data.retrofitrequest.PostModel
import com.example.a18.data.retrofitrequest.RequestModel
import com.example.a18.databinding.FragmentChangeFieldsBinding
import com.example.a18.ui.listfragment.ListFragmentComponent
import com.example.a18.ui.listfragment.MyViewModel
import dagger.android.support.DaggerFragment


class ChangeFieldsFragment : Fragment() {

    private val component by lazy { ListFragmentComponent.create() }

    private var _binding: FragmentChangeFieldsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ChangeFieldsFragmentArgs>()

    private val myViewModel: MyViewModel by viewModels{component.viewModelFactory()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangeFieldsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.idModel.text = args.currentModel.id
        binding.userIdModel.text = args.currentModel.userId
        binding.titleModel.setText(args.currentModel.title)
        binding.bodyModel.setText(args.currentModel.body)
        myViewModel.myResponse.observe(viewLifecycleOwner){
            if(it.isSuccessful){
                Log.i("isSuccessful",it.message())
            }else{
                Log.i("isWrong",it.code().toString())
            }
        }
        binding.changeModelButton.setOnClickListener {
            val model = PostModel(
                userId = binding.userIdModel.text.toString().toInt(),
                body = binding.bodyModel.text.toString(),
                title = binding.titleModel.text.toString()
            )
            try {
                myViewModel.postRequest(model)
            }catch (e : Exception){
                Log.e("Error in myViewModel", e.toString())
            }

            findNavController().navigate(R.id.action_changeFIeldsFragment_to_listFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }
}