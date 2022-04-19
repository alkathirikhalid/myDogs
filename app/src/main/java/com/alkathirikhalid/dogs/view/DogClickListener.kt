package com.alkathirikhalid.dogs.view

import android.view.View

interface DogClickListener {
    fun onDogClicked(uuid: Int, view: View)
}