package com.udacity.shoestore.screens.onboarding

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.WelcomeFragmentBinding

class WelcomeFragment : Fragment() {

    lateinit var binding: WelcomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.welcome_fragment, container, false)

        binding.nextButton.setOnClickListener {
            val action =
                WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        return binding.root
    }

}