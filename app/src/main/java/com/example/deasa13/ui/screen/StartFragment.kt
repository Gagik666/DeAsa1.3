package com.example.deasa13.ui.screen

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.deasa13.R
import com.example.deasa13.databinding.FragmentStartBinding
import com.example.deasa13.extensions.isRegistred
import com.example.deasa13.extensions.openFragment
import com.example.deasa13.viewModel.StartViewModel
import com.example.deasa13.viewModel.UserViewModel
import com.squareup.picasso.Picasso

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding
    lateinit var userViewModel: UserViewModel
    lateinit var startViewModel: StartViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        startViewModel = ViewModelProvider(this)[StartViewModel::class.java]
        if (isRegistred()) userViewModel.getUserData()
        binding.imgMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.nvMenu.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemSetings -> openFragment(R.id.action_startFragment_to_setingsFragment)
                R.id.itemRating -> openFragment(R.id.action_startFragment_to_retingFragment)
                R.id.itemUsers -> openFragment(R.id.action_startFragment_to_usersFragment)
            }
            true
        }
        val navMenuHeader = binding.nvMenu.getHeaderView(0)
        val name = navMenuHeader.findViewById<TextView>(R.id.tvUser)
        val img = navMenuHeader.findViewById<ImageView>(R.id.imgProfilHeader)

        if (isRegistred()) {
            userViewModel.user.observe(viewLifecycleOwner, Observer {
                name.text = "${it.firstName} ${it.lastName}"
                Picasso.get().load(it.imgURL).into(img)
            })
        }

        binding.btnPlayInTeams.setOnClickListener {
            startViewModel.setSingers()
            openFragment(R.id.action_startFragment_to_selectTeamFragment)
        }
    }

}