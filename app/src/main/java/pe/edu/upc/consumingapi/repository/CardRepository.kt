package pe.edu.upc.consumingapi.repository

import pe.edu.upc.consumingapi.BuildConfig
import pe.edu.upc.consumingapi.models.Card
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CardRepository{
    @Headers(
        "x-rapidapi-host: omgvamp-hearthstone-v1.p.rapidapi.com",
        "x-rapidapi-key: " + BuildConfig.API_KEY)
    @GET("/cards/{name}")
    fun getCardByName(@Path("name")cardName : String): Call<List<Card>>
}