package com.example.superhero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

//Intent de MAinActivity a DetailSuperheroActivity:4 private val onItemSelected:(String)-> Unit) //string pq la id q se pasa es string
class HeroAdapter(
    var superheros: List<SuperheroItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) : RecyclerView.Adapter<SuperheroViewHolder>() {

    fun updateList(superheros: List<SuperheroItemResponse>) {
        //this es el d la clase si no se llamam igual como aqui se puede sacar
        this.superheros = superheros
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        //val layoutInflater = LayoutInflater.from(parent.context)
        //return SuperheroViewHolder(layoutInflater.inflate((R.layout.item_hero_layout), parent, false))
        return SuperheroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_hero_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        //Intent de MAinActivity a DetailSuperheroActivity:5 pasa onItemselected, sin ()
        holder.render(superheros[position], onItemSelected)

    }

    override fun getItemCount(): Int {
        return superheros.size
    }
}