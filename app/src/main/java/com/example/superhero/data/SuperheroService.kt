package com.example.superhero

import android.util.Log
import androidx.core.view.isVisible
import com.example.superhero.data.SuperHeroClient
import com.example.superhero.data.SuperheroDataDetailResponse
import com.example.superhero.data.SuperheroDataResponse
import com.example.superhero.data.SuperheroItemResponse
import com.example.superhero.databinding.ActivityMainBinding
import retrofit2.Response
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

class SuperheroService @Inject constructor(private val retrofit: Retrofit) {


        suspend fun getSuperheros(superheroName: String): List<SuperheroItemResponse> {
            //(Dispatchers.IO) en hilo secundario
            return withContext(Dispatchers.IO) {
                val response =
                    retrofit.create(SuperHeroClient::class.java).getSuperheros(superheroName)
                response.body()?.resultsSuperhero ?: emptyList<SuperheroItemResponse>()
            }
        }


        suspend fun getSuperheroDetail(superheroId: String): SuperheroDataDetailResponse {
            //(Dispatchers.IO) en hilo secundario
            return withContext(Dispatchers.IO) {
                val response =
                    retrofit.create(SuperHeroClient::class.java).getSuperheroDetail(superheroId)
                response.body()!!.copy()
            }
        }


    }

