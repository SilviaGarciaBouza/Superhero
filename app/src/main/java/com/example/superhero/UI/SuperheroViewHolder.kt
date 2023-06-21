package com.example.superhero.UI

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superhero.data.SuperheroItemResponse
import com.example.superhero.databinding.ItemHeroLayoutBinding
import com.squareup.picasso.Picasso


class SuperheroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemHeroLayoutBinding.bind(view)
    fun render(item: SuperheroItemResponse, onItemSelected: (String) -> Unit) {
        binding.tvItemSuperhero.text = item.nameSuperhero
        //load(la imagen q quieres cargar) into(dnd quieres cargar la imagen)
        Picasso.get().load(item.imageSuperhero.urlSuperhero).into(binding.ivItemSuperhero)
        binding.root.setOnClickListener{onItemSelected(item.idSuperhero)}
    }
}





