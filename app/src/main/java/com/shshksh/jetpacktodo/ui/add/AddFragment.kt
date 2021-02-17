package com.shshksh.jetpacktodo.ui.add

import android.content.Context
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jakewharton.rxbinding4.widget.textChangeEvents
import com.shshksh.jetpacktodo.MainActivity
import com.shshksh.jetpacktodo.databinding.FragmentAddBinding
import com.shshksh.jetpacktodo.util.AppViewModelFactory
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: AppViewModelFactory

    private val viewModel: AddViewModel by viewModels { factory }

    private val compositeDisposable = CompositeDisposable()

    private val args: AddFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(args.id)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        factory = (context as MainActivity).obtainFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancelAdd.setOnClickListener { findNavController().navigateUp() }
        if (args.id >= 0) {
            binding.btnCompleteAdd.setOnClickListener {
                viewModel.update()
                findNavController().navigateUp()
            }
        } else {
            binding.btnCompleteAdd.setOnClickListener {
                viewModel.save()
                findNavController().navigateUp()
            }
        }

        setCompleteBtnWatcher()
    }

    private fun setCompleteBtnWatcher() {
        compositeDisposable.add(
            Observable.combineLatest(
                getTitleChangeEvent(),
                getDescriptionChangeEvent(),
                { _, _ ->
                    Pair(
                        binding.edittextTitleAdd.text.toString(),
                        binding.editDescriptionAdd.text.toString()
                    )
                }).subscribe {
                binding.btnCompleteAdd.isEnabled = it.first.isNotEmpty() && it.second.isNotEmpty()
            }
        )
    }

    private fun getTitleChangeEvent() = binding.edittextTitleAdd.textChangeEvents()

    private fun getDescriptionChangeEvent() = binding.editDescriptionAdd.textChangeEvents()

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        compositeDisposable.dispose()
    }
}