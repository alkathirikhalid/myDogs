package com.alkathirikhalid.dogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alkathirikhalid.dogs.model.DogBreed

class DetailViewModel : ViewModel() {
    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch() {
        val dog = DogBreed(
            "1",
            "Corgi",
            "15 years",
            "breedgroup",
            "for",
            "temp",
            "img"
        )
        dogLiveData.value = dog
    }
}