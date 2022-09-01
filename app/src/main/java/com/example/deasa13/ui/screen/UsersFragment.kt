package com.example.deasa13.ui.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.deasa13.DataList
import com.example.deasa13.adapter.UsersAdapter
import com.example.deasa13.databinding.FragmentUsersBinding
import com.example.deasa13.model.UserModel
import com.example.deasa13.model.UsersModel
import com.example.deasa13.viewModel.UsersViewModel
import com.google.android.material.tabs.TabLayoutMediator

class UsersFragment : Fragment() {
    lateinit var binding: FragmentUsersBinding
    lateinit var usersViewModel: UsersViewModel
    lateinit var usersAdapter: UsersAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersViewModel = ViewModelProvider(this)[UsersViewModel::class.java]

        usersViewModel.usersList.observe(viewLifecycleOwner, Observer {
            usersAdapter = UsersAdapter(it as MutableList<UsersModel>)
            binding.rvUsers.adapter = usersAdapter
        })

    }

}