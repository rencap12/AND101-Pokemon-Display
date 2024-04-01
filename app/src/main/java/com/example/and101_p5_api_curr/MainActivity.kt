package com.example.and101_p5_api_curr


import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.and101_p5_api_curr.R
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private lateinit var fetchPokemonButton: Button
    private lateinit var pokemonRecyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter
    private val pokemonList = mutableListOf<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchPokemonButton = findViewById(R.id.fetchPokemonButton)
        pokemonRecyclerView = findViewById(R.id.pokemonRecyclerView)
        pokemonAdapter = PokemonAdapter(pokemonList)
        pokemonRecyclerView.adapter = pokemonAdapter
        pokemonRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchPokemonButton.setOnClickListener {
            fetchPokemonList()
        }
    }

    private fun fetchPokemonList() {
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon?limit=20", object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch Pokémon list")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                try {
                    val results = json.jsonObject.getJSONArray("results")
                    for (i in 0 until results.length()) {
                        val pokemonObject = results.getJSONObject(i)
                        val pokemonName = pokemonObject.getString("name")
                        val pokemonUrl = pokemonObject.getString("url")
                        fetchPokemonDetails(pokemonName, pokemonUrl)
                    }
                } catch (e: JSONException) {
                    Log.e(TAG, "JSON Exception", e)
                }
            }
        }]
    }

    private fun fetchPokemonDetails(name: String, url: String) {
        val client = AsyncHttpClient()
        client[url, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch Pokémon details")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                try {
                    val pokemonId = json.jsonObject.getInt("id")
                    val pokemonTypes = json.jsonObject.getJSONArray("types")
                    val pokemonImageUrl = json.jsonObject.getJSONObject("sprites").getString("front_default")
                    val pokemonHeight = json.jsonObject.getInt("height")
                    val pokemonWeight = json.jsonObject.getInt("weight")
                    val pokemon = Pokemon(name, pokemonId, getPokemonTypes(pokemonTypes), pokemonImageUrl, pokemonHeight, pokemonWeight)

                    pokemonList.add(pokemon)
                    pokemonAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    Log.e(TAG, "JSON Exception", e)
                }
            }
        }]
    }

    private fun getPokemonTypes(types: JSONArray): String {
        val typeList = mutableListOf<String>()
        for (i in 0 until types.length()) {
            val type = types.getJSONObject(i).getJSONObject("type").getString("name")
            typeList.add(type)
        }
        return typeList.joinToString(", ")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

data class Pokemon(val name: String, val id: Int, val types: String, val imageUrl: String, val height: Int, val weight: Int)

