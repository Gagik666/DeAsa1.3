package com.example.deasa13.ui.screen

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.recreate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deasa13.DataList
import com.example.deasa13.R
import com.example.deasa13.Value
import com.example.deasa13.adapter.TeamAdapter
import com.example.deasa13.databinding.FragmentSelectTeamBinding
import com.example.deasa13.extensions.openFragment
import com.example.deasa13.model.TeamModel
import com.example.deasa13.viewModel.SelsectViewModel

class SelectTeamFragment : Fragment() {
    private lateinit var binding: FragmentSelectTeamBinding
    lateinit var teamAdapter: TeamAdapter
    lateinit var selectViewModel: SelsectViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectTeamBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectViewModel = ViewModelProvider(this)[SelsectViewModel::class.java]
        selectViewModel.tempList.observe(viewLifecycleOwner, Observer {
            teamAdapter = TeamAdapter(it as MutableList<TeamModel>) { index ->
                findNavController().navigate(
                    SelectTeamFragmentDirections.actionSelectTeamFragmentToTeamDialogFragment(
                        index
                    )
                )
            }
            binding.rvTeam.adapter = teamAdapter
        })



        if (DataList.teamList.size <= 3)
            binding.tvAddTeam.visibility = View.VISIBLE
        else
            binding.tvAddTeam.visibility = View.INVISIBLE
        binding.tvAddTeam.setOnClickListener {
            selectViewModel.addTeam()
            recreate(context as Activity)
        }

        binding.btnStartSinger.setOnClickListener {
            openFragment(R.id.action_selectTeamFragment_to_deAsaStoageFragment)
            selectViewModel.shuffle()
            when (binding.rdGrupSetings.checkedRadioButtonId) {
                R.id.rdBtn60Sec -> Value.timer = 10
                R.id.rdBtn90sec -> Value.timer = 60
                R.id.rdBtn120sec -> Value.timer = 75
            }

        }
    }
}