package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.screens.login.LoginFragmentDirections


class ShoeListFragment : Fragment() {

    lateinit var binding: ShoeListFragmentBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.shoe_list_fragment, container, false)


        //Adding views used this link that was already included in the udacity classroom
        //https://stackoverflow.com/questions/2395769/how-to-programmatically-add-views-to-views

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
        binding.fab1.setOnClickListener {
            onFloatingButtonClick()
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
    }

    //Udacity instructor pointed to this https://medium.com/tech-takeaways/how-to-migrate-the-deprecated-oncreateoptionsmenu-b59635d9fe10
    // to use instead of the deprecated onCreateOptionsMenu

    private fun setupMenu() {
        val menuHost = (requireActivity() as MenuHost)
        menuHost.addMenuProvider(
            object : MenuProvider {

                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.logout_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    logOut()
                    return true
                }

            },
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )
    }

    private fun onFloatingButtonClick() {
        val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun logOut() {
        val action = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}