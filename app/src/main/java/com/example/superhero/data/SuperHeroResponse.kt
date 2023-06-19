package com.example.superhero.data

import com.google.gson.annotations.SerializedName

/*
//AL buscar por name
//@SerializedName("response") el response  y el resultses el nombre q tiene en el json.
data class SuperheroDataResponse(
    @SerializedName("response") val responseSuperhero:String,
    @SerializedName("results") val resultsSuperhero: List<SuperheroItemResponse>
)
//al desplegar results aparecen esto:
data class SuperheroItemResponse(
    @SerializedName("id") val idSuperhero: String,
    //  @SerializedName("image") val imageSuperhero: String,
    @SerializedName("name") val nameSuperhero: String,
    @SerializedName("image") val imageSuperhero: SuperheroImageResponse
)
//al desplegar image aparece esto:
data class SuperheroImageResponse(
    @SerializedName("url") val urlSuperhero: String
)



//al buscar por el id:
data class SuperheroDataDetailResponse(
    @SerializedName("name") val nameSuperheroDetail: String,
    @SerializedName("powerstats") val powerstatsSuperheroDetail: SuperheroPowerstatsResponse,
    @SerializedName("biography") val biographySuperheroDetail: SuperheroBiographyResponse,
    @SerializedName("image") val imageSuperheroDetail: SuperherImageResponse
)
data class SuperherImageResponse(
    @SerializedName("url") val urlSuperheroDetail: String
)
data class SuperheroPowerstatsResponse(
    @SerializedName("intelligence") val intelligenceSuperheroDetail: String,
    @SerializedName("strength") val strengthSuperheroDetail: String,
    @SerializedName("speed") val speedSuperheroDetail: String,
    @SerializedName("durability") val durabilitySuperheroDetail: String,
    @SerializedName("power") val poweruperheroDetail: String,
    @SerializedName("combat") var combatSuperheroDetail: String,
)

data class SuperheroBiographyResponse(
    @SerializedName("full-name") var fullNameSuperheroDetail: String
)
data class SuperheroAliasesResponse(
    @SerializedName("publisher") var publisherSuperheroDetail:String
)*/