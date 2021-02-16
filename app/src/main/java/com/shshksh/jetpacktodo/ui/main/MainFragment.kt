package com.shshksh.jetpacktodo.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shshksh.jetpacktodo.MainActivity
import com.shshksh.jetpacktodo.databinding.FragmentMainBinding
import com.shshksh.jetpacktodo.util.AppViewModelFactory
import io.reactivex.rxjava3.disposables.Disposable

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: AppViewModelFactory

    private val viewModel: MainViewModel by viewModels { factory }

    private var disposable: Disposable? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        factory = (context as MainActivity).obtainFactory()
        viewModel.loadTodo()
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
        val adapter = TodoAdapter()
        binding.recyclerviewMain.adapter = adapter

        viewModel.liveTodo.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        disposable = viewModel.todoClickEvent.subscribe {
            Log.d(this::class.simpleName, "onViewCreated: subscribe $it")
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddFragment(it.todo))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        disposable?.dispose()
    }
}