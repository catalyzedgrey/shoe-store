package com.udacity.shoestore.screens.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import kotlin.random.Random

class ShoeListViewModel : ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

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
    }

    fun addShoe(shoe: Shoe) {
        _shoeList.value?.add(shoe)
    }
}