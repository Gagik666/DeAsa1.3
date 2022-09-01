package com.example.deasa13.ui.screen
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.deasa13.R
import com.example.deasa13.databinding.FragmentSetingsBinding
import com.example.deasa13.extensions.isRegistred
import com.example.deasa13.extensions.openFragment
import com.example.deasa13.viewModel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class SetingsFragment : Fragment() {
    private lateinit var binding: FragmentSetingsBinding
    lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSetingsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        if (isRegistred()) userViewModel.getUserData()
        binding.apply {
            if (isRegistred()) {
                flowLogOut.visibility = View.VISIBLE
                flowLogIn.visibility = View.INVISIBLE
                imgMore.visibility = View.VISIBLE
            } else {
                flowLogOut.visibility = View.INVISIBLE
                flowLogIn.visibility = View.VISIBLE
                imgMore.visibility = View.INVISIBLE
            }

            flowLogIn.setOnClickListener {
                openFragment(R.id.action_setingsFragment_to_loginFragment)
            }

            binding.flowLogOut.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                openFragment(R.id.action_setingsFragment_to_startFragment)
            }

            userViewModel.user.observe(viewLifecycleOwner, Observer {
                binding.tvUserName.text = "${it.firstName} ${it.lastName}"
                Picasso.get().load(it.imgURL).into(binding.imgProfil)
            })
        }

    }

}