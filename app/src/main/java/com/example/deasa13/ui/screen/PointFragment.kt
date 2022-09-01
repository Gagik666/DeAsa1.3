package com.example.deasa13.ui.screen

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.deasa13.DataList
import com.example.deasa13.R
import com.example.deasa13.adapter.OldPointAdapter
import com.example.deasa13.adapter.PointAdapter
import com.example.deasa13.databinding.FragmentPointBinding
import com.example.deasa13.extensions.dialog
import com.example.deasa13.extensions.openFragment
import com.example.deasa13.model.TeamModel
import com.example.deasa13.viewModel.PointViewModel

class PointFragment : Fragment() {
    private lateinit var binding: FragmentPointBinding
    lateinit var pointViewModel: PointViewModel
    lateinit var pointAdapter: PointAdapter
    lateinit var oldPoinAdapter: OldPointAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPointBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pointViewModel = ViewModelProvider(this)[PointViewModel::class.java]

        pointViewModel.teamList.observe(viewLifecycleOwner, Observer {
            pointAdapter = PointAdapter(it as MutableList<TeamModel>)
            binding.rvTeamPoint.adapter = pointAdapter
        })

        binding.btnRefreshResults.setOnClickListener {
            pointViewModel.refreshData()
            binding.rvTeamPointOlden.visibility = View.INVISIBLE
        }

        binding.tvShowhResult.setOnClickListener {
            pointViewModel.getTeamInfo()
            binding.rvTeamPointOlden.visibility = View.VISIBLE
            binding.tvShowhResult.isClickable = false
        }


        pointViewModel.oldTeamList.observe(viewLifecycleOwner, Observer {
            oldPoinAdapter = OldPointAdapter(it as MutableList<String>)
            binding.rvTeamPointOlden.adapter = oldPoinAdapter
        })

        pointViewModel.checkInternet.observe(viewLifecycleOwner, Observer {
            if (!it) {
                this.context?.let { it1 -> dialog(it1, "There is not conection internet") }
            }
        })



        binding.btnPlay.setOnClickListener {
            pointViewModel.updateTeam()
            DataList.oldTeamList.clear()
            openFragment(R.id.action_pointFragment_to_deAsaStoageFragment)
        }

        pointViewModel.gameOver.observe(viewLifecycleOwner, Observer {
            binding.apply {
                if (it) {
                    btnPlay.visibility = View.INVISIBLE
                    btnAgain.visibility = View.VISIBLE
                    btnRefreshResults.visibility = View.VISIBLE
                    tvShowhResult.visibility = View.VISIBLE
                } else {
                    btnPlay.visibility = View.VISIBLE
                    btnAgain.visibility = View.INVISIBLE
                    btnRefreshResults.visibility = View.INVISIBLE
                    tvShowhResult.visibility = View.INVISIBLE
                }
            }
        })

        binding.btnAgain.setOnClickListener {
            pointViewModel.playAgain()
            openFragment(R.id.action_pointFragment_to_startFragment)
            binding.rvTeamPointOlden.visibility = View.INVISIBLE
            binding.tvShowhResult.isClickable = true
        }


    }

}