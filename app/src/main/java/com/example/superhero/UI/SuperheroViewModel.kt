package com.example.superhero.UI

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class SuperheroViewModel @Inject constructor(val retrofit: Retrofit): ViewModel() {
}