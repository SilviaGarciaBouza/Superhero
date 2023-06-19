package com.example.superhero.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/*

interface SuperHeroClient {
    //La acción, en este caso GET y le pones la parte finalde lo q llamas (en este llamas por name)
    @GET("/api/3373221366270737/search/{name}") //name es lo q va a cambiar por lo q ponga en la búsqueda
    //las fun de las corrutinas van con suspend
    //"name" es lo q va a cambiar. devuelve una Response
    suspend fun getSuperheros(@Path("name") superheroName: String): Response<SuperheroDataResponse>
    @GET("/api/3373221366270737/{id}")
    suspend fun getSuperheroDetail(@Path("id") superheroId: String): Response<SuperheroDataDetailResponse>
}*/