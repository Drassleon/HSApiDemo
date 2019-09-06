package pe.edu.upc.consumingapi.configuration

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class RetrofitConfig{

    private var retrofit: Retrofit? = null
    private val baseURL = "https://omgvamp-hearthstone-v1.p.rapidapi.com"

    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

}