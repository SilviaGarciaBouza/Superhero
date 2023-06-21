package com.example.superhero

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superhero.DetailSuperheroActivity.Companion.EXTRA_ID
import com.example.superhero.UI.HeroAdapter
import com.example.superhero.UI.SuperheroViewModel
import com.example.superhero.data.SuperHeroClient
import com.example.superhero.data.SuperheroDataResponse
import com.example.superhero.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

//TODO se cierra cuando algun nombr no tiene coincidencias

//TODO añadi el client y els service
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var adapterSuperhero: HeroAdapter
    private val viewModel: SuperheroViewModel by viewModels()

    //RETROFIT: Crea un elemento retrofit: 2
   //private lateinit var retrofit: Retrofit
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //RETROFIT: Crea un elemento retrofit: 3
        // retrofit = getRetrofit()
        initUI()
    }

    //Intent de MAinActivity a DetailSuperheroActivity:1
    private fun navigateToDetailSuerheroActivity(id: String) {
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }

    private fun initUI() {
        binding.svListSuperhero.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            //fun q se llama al darle al botón de buscar
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Se llama a la fun q quieras.En esta fun no lo qremos ? así q ponemos o empty
                searchSuperheroByName(query.orEmpty())
                return false
            }

            //fun q se llama cuando se va escribiendo
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        //Intent de MAinActivity a DetailSuperheroActivity:2 (después de HeroAdapter()
        adapterSuperhero = HeroAdapter { navigateToDetailSuerheroActivity(it) }
        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvSuperhero.adapter = adapterSuperhero
    }


    private fun searchSuperheroByName(query: String) {
        //se pone true o false. siempre se hacen en hilomppal
        binding.pbSuperhero.isVisible = true
        //IO para q to lo q sta entre {} se haga en el hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperheroDataResponse> =
                viewModel.retrofit.create(SuperHeroClient::class.java).getSuperheros(query)
               // retrofit.create(SuperheroService::class.java).getSuperheros(query)
            if (myResponse.isSuccessful) {
                Log.i("SearchProblem", "funciona")
                val response: SuperheroDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("SearchProblem", response.toString())
                    //las viestas tienen q ir en hilo ppal y no en secundarios
                    runOnUiThread {
                        adapterSuperhero.updateList(response.resultsSuperhero)
                        binding.pbSuperhero.isVisible = false
                    }
                }
            } else {
                Log.i("SearchProblem", "No funciona")
            }
        }
    }
/*
    //RETROFIT: Crea el retrofut y el intercptor
    private fun getRetrofit(): Retrofit {
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
    */


}




