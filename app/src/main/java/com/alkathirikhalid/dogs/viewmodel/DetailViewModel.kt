package com.alkathirikhalid.dogs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.alkathirikhalid.dogs.model.DogBreed
import com.alkathirikhalid.dogs.model.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch(uuid: Int) {
        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(uuid)
            dogLiveData.value = dog
        }
    }
}