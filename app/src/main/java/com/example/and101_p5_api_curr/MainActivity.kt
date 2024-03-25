package com.example.and101_p5_api_curr

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

import com.example.and101_p5_api_curr.R
import okhttp3.Headers
import okhttp3.internal.http2.Header

class MainActivity : AppCompatActivity() {

    private lateinit var fetchPokemonButton: Button
    private lateinit var pokemonImageView: ImageView
    private lateinit var pokemonNameTextView: TextView
    private lateinit var pokemonIdTextView: TextView
    private lateinit var pokemonTypeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchPokemonButton = findViewById(R.id.fetchPokemonButton)
        pokemonImageView = findViewById(R.id.pokemonImageView)
        pokemonNameTextView = findViewById(R.id.pokemonNameTextView)
        pokemonIdTextView = findViewById(R.id.pokemonIdTextView)
        pokemonTypeTextView = findViewById(R.id.pokemonTypeTextView)

        fetchPokemonButton.setOnClickListener {
            fetchRandomPokemon()
        }
    }

    private fun fetchRandomPokemon() {
        val client = AsyncHttpClient()
        client["https://pokeapi.co/api/v2/pokemon/${getRandomPokemonId()}", object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) { Log.e(TAG, "Failed to fetch Pokémon data")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                try {
                    val pokemonName = json.jsonObject.getString("name")
                    val pokemonId = json.jsonObject.getInt("id")
                    val pokemonTypes = json.jsonObject.getJSONArray("types").getJSONObject(0).getJSONObject("type").getString("name")
                    val pokemonImageUrl = json.jsonObject.getJSONObject("sprites").getString("front_default")

                    updateUI(pokemonName, pokemonId, pokemonTypes, pokemonImageUrl)
                } catch (e: JSONException) {
                    Log.e(TAG, "JSON Exception", e)
                }
            }

        }]
    }

    private fun updateUI(name: String, id: Int, types: String, imageUrl: String) {
        pokemonNameTextView.text = "Name: $name"
        pokemonIdTextView.text = "ID: $id"
        pokemonTypeTextView.text = "Type: $types"
        LoadImageFromUrl(pokemonImageView).execute(imageUrl)
    }

    private fun getRandomPokemonId(): Int {
        return (1..898).random() // Total number of Pokémon in the PokeAPI is 898
    }

    private inner class LoadImageFromUrl(private val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
        override fun doInBackground(vararg urls: String): Bitmap? {
            val imageUrl = urls[0]
            var bitmap: Bitmap? = null
            try {
                val inputStream: InputStream = URL(imageUrl).openStream()
                bitmap = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                Log.e(TAG, "Error loading image from URL", e)
            }
            return bitmap
        }

        override fun onPostExecute(result: Bitmap?) {
            imageView.setImageBitmap(result)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
