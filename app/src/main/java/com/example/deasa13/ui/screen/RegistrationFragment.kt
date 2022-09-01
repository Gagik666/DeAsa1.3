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
import com.example.deasa13.R
import com.example.deasa13.databinding.FragmentRegistrationBinding
import com.example.deasa13.extensions.*
import com.example.deasa13.viewModel.AuthenticationViewModel

class RegistrationFragment : Fragment() {
    private lateinit var binding: FragmentRegistrationBinding
    lateinit var authenticationViewModel: AuthenticationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        binding.btnRegistration.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            if (binding.edFirstName.text.toString().isEmpty()) {
                binding.edFirstName.error = ("firstName is empty")
            } else if (binding.edLastName.text.toString().isEmpty()) {
                binding.edLastName.error = ("lastName is empty")
            } else if (!isValidEmail(binding.edEmail.text.toString())) {
                binding.edEmail.error = ("email is invalide")
            } else if (binding.edPassword.text.toString().isEmpty()) {
                binding.edPassword.error = ("password is empty")
            } else {
                authenticationViewModel.registraton(
                    binding.edFirstName.text.toString(),
                    binding.edLastName.text.toString(),
                    binding.edEmail.text.toString(),
                    binding.edPassword.text.toString()
                )
            }
        }

        authenticationViewModel.internet.observe(viewLifecycleOwner, Observer {
            dialog("There is not conection internet")
            binding.progressBar.visibility = View.INVISIBLE
        })

        authenticationViewModel.isRegistreted.observe(viewLifecycleOwner, Observer {
            if (it) {
                openFragment(R.id.action_registrationFragment_to_loginFragment)
                binding.progressBar.visibility = View.INVISIBLE
                emailVerifiDialog(getUrl("https://${binding.edEmail.text}"))
            }
        })
    }

}