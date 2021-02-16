package com.shshksh.jetpacktodo.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shshksh.jetpacktodo.MainActivity
import com.shshksh.jetpacktodo.databinding.FragmentMainBinding
import com.shshksh.jetpacktodo.util.AppViewModelFactory

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: AppViewModelFactory

    private val viewModel: MainViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        factory = (context as MainActivity).obtainFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addTodoMain.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddFragment())
        }
//        binding.recyclerviewMain.adapter = TodoAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}