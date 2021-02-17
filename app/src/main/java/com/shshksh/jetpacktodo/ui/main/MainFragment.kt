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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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

    private val adapter = TodoAdapter()

    private val simpleCallBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false;
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.layoutPosition
            viewModel.removeTodo(position)
        }
    }

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
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToAddFragment()
            )
        }
        binding.recyclerviewMain.adapter = adapter
        val itemTouchHelper = ItemTouchHelper(simpleCallBack)
        itemTouchHelper.attachToRecyclerView(binding.recyclerviewMain)

        viewModel.liveTodo.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        disposable = viewModel.todoClickEvent.subscribe {
            Log.d(this::class.simpleName, "onViewCreated: subscribe $it")
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToAddFragment(it.todo.id)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        disposable?.dispose()
    }
}