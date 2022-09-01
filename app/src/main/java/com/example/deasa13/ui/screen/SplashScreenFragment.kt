package com.example.deasa13.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.deasa13.R
import com.example.deasa13.databinding.FragmentSplashScreenBinding
import com.example.deasa13.extensions.openFragment
import com.example.deasa13.viewModel.SplashScreenViewModel

class SplashScreenFragment : Fragment() {
    lateinit var binding: FragmentSplashScreenBinding
    lateinit var splashScreenViewModel: SplashScreenViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashScreenViewModel = ViewModelProvider(this)[SplashScreenViewModel::class.java]
        splashScreenViewModel.size.observe(viewLifecycleOwner, Observer {
            binding.tvTeatle.textSize = it.toFloat()
        })

        splashScreenViewModel.splashEnd.observe(viewLifecycleOwner, Observer {
            if (it)
                openFragment(R.id.action_splashScreenFragment_to_startFragment)
        })
    }
}