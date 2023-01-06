package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.screens.login.LoginFragmentDirections


class ShoeListFragment : Fragment() {

    lateinit var binding: ShoeListFragmentBinding
    lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.shoe_list_fragment, container, false)

        viewModel = ViewModelProvider(this)[ShoeListViewModel::class.java]
        setHasOptionsMenu(true)
        viewModel.shoeList.observe(viewLifecycleOwner) {
            viewModel.shoeList.value?.let { shoeList ->
                for (shoe in shoeList) {
                    val shoeItemBinding = ShoeItemBinding.inflate(layoutInflater)
                    shoeItemBinding.shoe = shoe

                    val shoeView = shoeItemBinding.root

                    shoeView.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT
                    )

                    binding.linearLayout.addView(shoeView)
                }
            }
        }

        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        logOut()
        return super.onOptionsItemSelected(item)
    }

    private fun logOut() {
        val action = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}