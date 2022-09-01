package com.example.deasa13.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.example.deasa13.DataList
import com.example.deasa13.R
import com.example.deasa13.databinding.FragmentTeamDialogBinding
import com.example.deasa13.extensions.openFragment

class TeamDialogFragment : DialogFragment() {
    lateinit var binding: FragmentTeamDialogBinding
    private val args: TeamDialogFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.text = DataList.teamList[args.index].team

        binding.btnOk.setOnClickListener {
            openFragment(R.id.action_teamDialogFragment_to_selectTeamFragment)
            if (binding.edName.text.isEmpty())
                DataList.teamList[args.index].team = binding.tvName.text.toString()
            else
                DataList.teamList[args.index].team = binding.edName.text.toString()
            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }


}