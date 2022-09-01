package com.example.deasa13.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.deasa13.R
import com.example.deasa13.Value
import com.example.deasa13.adapter.SingerAdapter
import com.example.deasa13.databinding.FragmentDeAsaStoageBinding
import com.example.deasa13.extensions.openFragment
import com.example.deasa13.viewModel.DeAsaViewModel

class DeAsaStoageFragment : Fragment() {
    lateinit var binding: FragmentDeAsaStoageBinding
    lateinit var deAsaViewModel: DeAsaViewModel
    lateinit var singerAdapter: SingerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeAsaStoageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deAsaViewModel = ViewModelProvider(this)[DeAsaViewModel::class.java]
        deAsaViewModel.getSingerTempList()
        binding.tvSeconds.text = Value.timer.toString()
        deAsaViewModel.second.observe(viewLifecycleOwner, Observer {
            binding.tvSeconds.text = it.toString()
            if (it == 0)
                openFragment(R.id.action_deAsaStoageFragment_to_pointFragment)
        })

        deAsaViewModel.teamName.observe(viewLifecycleOwner, Observer {
            binding.tvTeam.text = it
        })

        deAsaViewModel.tempList.observe(viewLifecycleOwner, Observer {
            singerAdapter = SingerAdapter(it as MutableList<String>) {click ->
                deAsaViewModel.updatePoint(click)
            }
            binding.rvSinger.adapter = singerAdapter
        })

        deAsaViewModel.updateTempList.observe(viewLifecycleOwner, Observer {
            if (it) {
                singerAdapter.notifyDataSetChanged()
            }
        })

        deAsaViewModel.tempPoint.observe(viewLifecycleOwner, Observer {
            binding.tvPoint.text = "point $it  "
        })
    }
}