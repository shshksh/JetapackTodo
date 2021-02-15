package com.shshksh.jetpacktodo.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shshksh.jetpacktodo.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var binding: FragmentAddBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}