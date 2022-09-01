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
import com.example.deasa13.databinding.FragmentLoginBinding
import com.example.deasa13.extensions.*
import com.example.deasa13.viewModel.AuthenticationViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    lateinit var authenticationViewModel: AuthenticationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authenticationViewModel = ViewModelProvider(this)[AuthenticationViewModel::class.java]
        binding.tvRegistration.setOnClickListener {
            openFragment(R.id.action_loginFragment_to_registrationFragment)
        }

        binding.btnLogIn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            if (!isValidEmail(binding.edEmail.text.toString())) {
                binding.edEmail.error = ("email is invalide")
                binding.edEmail.requestFocus()
            } else if (binding.edPassword.text.toString().isEmpty()) {
                binding.edPassword.error = ("password is empty")
            } else {
                authenticationViewModel.logIn(
                    binding.edEmail.text.toString(),
                    binding.edPassword.text.toString()
                )
            }

            authenticationViewModel.internet.observe(viewLifecycleOwner, Observer {
                dialog("There is not conection internet")
                binding.progressBar.visibility = View.INVISIBLE
            })

            authenticationViewModel.isLogIn.observe(viewLifecycleOwner, Observer {
                if (it) {
                    openFragment(R.id.action_loginFragment_to_startFragment)
                    binding.progressBar.visibility = View.INVISIBLE
                } else {
                    emailVerifiDialog(getUrl("https://${binding.edEmail.text}"))
                    binding.progressBar.visibility = View.INVISIBLE
                }
            })
        }
    }


}