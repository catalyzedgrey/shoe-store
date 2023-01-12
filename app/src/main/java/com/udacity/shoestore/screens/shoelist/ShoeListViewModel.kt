package com.udacity.shoestore.screens.shoelist

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import java.lang.Double
import kotlin.random.Random

class ShoeListViewModel : ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    val shoe = MutableLiveData<Shoe>()


    init {
        val mutableShoeList = mutableListOf<Shoe>()

        for (i in 0..10) {
            mutableShoeList.add(
                Shoe(
                    name = "shoe $i",
                    company = "company $i",
                    description = "Description $i",
                    size = Random.nextInt(30, 45).toDouble(),
                )
            )
        }
        _shoeList.value = mutableShoeList
        shoe.value = Shoe(
            name = "",
            size = 0.0,
            description = "",
            images = listOf(),
            company = ""
        )
    }

    fun addShoe() {
        shoe.value?.let { _shoeList.value?.add(it) }
    }
}