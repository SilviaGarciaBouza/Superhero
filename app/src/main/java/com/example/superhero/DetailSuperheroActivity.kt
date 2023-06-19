package com.example.superhero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.superhero.databinding.ActivityDetailSuperheroBinding
import com.example.superhero.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
@AndroidEntryPoint
class DetailSuperheroActivity : AppCompatActivity() {
    //Intent de MAinActivity a DetailSuperheroActivity:3
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var retrofit: Retrofit
    private lateinit var binding: ActivityDetailSuperheroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //vamos a coger los datos de internet. getString pq la id es una string, sino seria getBoolean o lo q sea
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        retrofit = getRetrofitDetail()
        getSuperheros(id)

    }

    private fun getSuperheros(id: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperheroDataDetailResponse> =
                retrofit.create(ApiService::class.java).getSuperheroDetail(id)
            if (myResponse.isSuccessful) {
               val response: SuperheroDataDetailResponse? = myResponse.body()
               if (response != null) {
                    //las viestas tienen q ir en hilo ppal y no en secundarios
                    runOnUiThread {
                        paintCharacter(response)

                    }
                }
            } else {
                Log.i("SearchProblem", "No funciona")
            }
        }

    }

    private fun paintCharacter(item: SuperheroDataDetailResponse) {
        updateHeight(
            binding.vIntelligence,
            item.powerstatsSuperheroDetail.intelligenceSuperheroDetail.toInt()
        )
        updateHeight(binding.vCombat, item.powerstatsSuperheroDetail.combatSuperheroDetail.toInt())
        updateHeight(binding.vPower, item.powerstatsSuperheroDetail.poweruperheroDetail.toInt())
        updateHeight(binding.vSpeed, item.powerstatsSuperheroDetail.speedSuperheroDetail.toInt())
        updateHeight(
            binding.vDurability,
            item.powerstatsSuperheroDetail.durabilitySuperheroDetail.toInt()
        )
        updateHeight(
            binding.vStrength,
            item.powerstatsSuperheroDetail.strengthSuperheroDetail.toInt()
        )
        binding.tvDetailName.text = item.nameSuperheroDetail
        binding.tvDetailFullName.text = item.biographySuperheroDetail.fullNameSuperheroDetail

        Picasso.get().load(item.imageSuperheroDetail.urlSuperheroDetail).into(binding.ivDetail)

    }

    private fun updateHeight(view: View, power: Int) {
        val param = view.layoutParams
        param.height = power
        view.layoutParams = param
    }

    //RETROFIT: Crea el retrofut
    private fun getRetrofitDetail(): Retrofit {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = builder
            .addInterceptor(logging)
            .build()
        val retrofit = Retrofit
            .Builder()
            .client(client)
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

}