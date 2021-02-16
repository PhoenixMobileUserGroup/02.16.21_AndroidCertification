package com.neudesic.pmug02_16_2021_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.neudesic.pmug02_16_2021_kotlin.model.Person

class PersonViewModel(app: Application) : AndroidViewModel(app) {
    private val personsData: MutableLiveData<List<Person>> = MutableLiveData()

    val data: LiveData<List<Person>> = personsData

    init {
        //instantiate the room database here
    }

}