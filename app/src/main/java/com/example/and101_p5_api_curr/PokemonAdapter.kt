package com.example.and101_p5_api_curr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.and101_p5_api_curr.R

class PokemonAdapter(private val pokemonList: List<Pokemon>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonImage: ImageView = itemView.findViewById(R.id.pokemon_image)
        val pokemonName: TextView = itemView.findViewById(R.id.pokemon_name)
        val pokemonHeight: TextView = itemView.findViewById(R.id.pokemon_height)
        val pokemonWeight: TextView = itemView.findViewById(R.id.pokemon_weight)
        val separator: View = itemView.findViewById(R.id.separator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.pokemonName.text = "Name: ${pokemon.name}"
        holder.pokemonHeight.text = "Height: ${pokemon.height}"
        holder.pokemonWeight.text = "Weight: ${pokemon.weight}"
        Glide.with(holder.itemView.context).load(pokemon.imageUrl).into(holder.pokemonImage)

         if (position == itemCount - 1) {
         holder.separator.visibility = View.GONE // Hide separator for the last item
        } else {
                holder.separator.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}
