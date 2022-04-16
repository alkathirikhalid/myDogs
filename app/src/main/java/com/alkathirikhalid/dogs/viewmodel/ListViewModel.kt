package com.alkathirikhalid.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alkathirikhalid.dogs.model.DogBreed

class ListViewModel : ViewModel() {

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    // Test Data
    fun refresh() {
        val dog1 = DogBreed(
            "1",
            "Corgi",
            "15 years",
            "breedgroup",
            "for",
            "temp",
            "img"
        )
        val dog2 = DogBreed(
            "2",
            "Mgi",
            "10 years",
            "breedgroup",
            "for",
            "temp",
            "img"
        )
        val dog3 = DogBreed(
            "3",
            "Sgi",
            "20 years",
            "breedgroup",
            "for",
            "temp",
            "img"
        )

        val dogList: ArrayList<DogBreed> = arrayListOf(dog1, dog2, dog3)

        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }
}