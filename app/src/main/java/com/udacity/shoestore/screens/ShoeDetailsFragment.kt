package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailsLayoutBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.shoelist.ShoeListViewModel
import kotlin.random.Random

class ShoeDetailsFragment : Fragment() {
    lateinit var binding: ShoeDetailsLayoutBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.shoe_details_layout, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.addShoeButton.setOnClickListener {
            addShoe()
        }

        binding.cancelButton.setOnClickListener {
            onCancelClick()
        }

        return binding.root
    }

    private fun onCancelClick() {
        NavHostFragment.findNavController(this).navigateUp()
    }

    private fun addShoe() {
        viewModel.addShoe()
        NavHostFragment.findNavController(this).navigateUp()
    }
}