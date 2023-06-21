package com.example.superhero.data

import com.example.superhero.SuperheroService
import javax.inject.Inject

class SuperheroRepositoty @Inject constructor(private val api: SuperheroService) {
    suspend fun getSuperheros(superheroName: String):List<SuperheroItemResponse>{
        return api.getSuperheros(superheroName)
    }

    suspend fun getSuperheroDetail(superheroId: String): SuperheroDataDetailResponse{
        return api.getSuperheroDetail(superheroId)
    }
}
