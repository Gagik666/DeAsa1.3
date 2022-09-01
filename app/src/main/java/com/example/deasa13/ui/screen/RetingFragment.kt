package com.example.deasa13.ui.screen

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.deasa13.R
import com.example.deasa13.databinding.FragmentRetingBinding
import com.example.deasa13.extensions.openFragment
import com.example.deasa13.viewModel.RetingViewModel

class RetingFragment() : Fragment(){
    lateinit var binding: FragmentRetingBinding
    lateinit var ratingViewModel: RetingViewModel
    private var rating = 0;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRetingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ratingViewModel = ViewModelProvider(this)[RetingViewModel::class.java]
        binding.apply {
            imgStar1.setOnClickListener {
                imgStar1.setImageResource(R.drawable.ic_activ_star)
                imgStar2.setImageResource(R.drawable.ic_star)
                imgStar3.setImageResource(R.drawable.ic_star)
                imgStar4.setImageResource(R.drawable.ic_star)
                imgStar5.setImageResource(R.drawable.ic_star)
                rating = 1
            }

            imgStar2.setOnClickListener {
                imgStar1.setImageResource(R.drawable.ic_activ_star)
                imgStar2.setImageResource(R.drawable.ic_activ_star)
                imgStar3.setImageResource(R.drawable.ic_star)
                imgStar4.setImageResource(R.drawable.ic_star)
                imgStar5.setImageResource(R.drawable.ic_star)
                rating = 2
            }

            imgStar3.setOnClickListener {
                imgStar1.setImageResource(R.drawable.ic_activ_star)
                imgStar2.setImageResource(R.drawable.ic_activ_star)
                imgStar3.setImageResource(R.drawable.ic_activ_star)
                imgStar4.setImageResource(R.drawable.ic_star)
                imgStar5.setImageResource(R.drawable.ic_star)
                rating = 3
            }

            imgStar4.setOnClickListener {
                imgStar1.setImageResource(R.drawable.ic_activ_star)
                imgStar2.setImageResource(R.drawable.ic_activ_star)
                imgStar3.setImageResource(R.drawable.ic_activ_star)
                imgStar4.setImageResource(R.drawable.ic_activ_star)
                imgStar5.setImageResource(R.drawable.ic_star)
                rating = 4
            }

            imgStar5.setOnClickListener {
                imgStar1.setImageResource(R.drawable.ic_activ_star)
                imgStar2.setImageResource(R.drawable.ic_activ_star)
                imgStar3.setImageResource(R.drawable.ic_activ_star)
                imgStar4.setImageResource(R.drawable.ic_activ_star)
                imgStar5.setImageResource(R.drawable.ic_activ_star)
                rating = 5
            }

        }

        binding.btnSave.setOnClickListener {
            ratingViewModel.saveData(rating)
            binding.progressBar.visibility = View.VISIBLE

        }

        ratingViewModel.isOk.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressBar.visibility = View.INVISIBLE
                openFragment(R.id.action_retingFragment_to_startFragment)
            }
        })

    }

}

