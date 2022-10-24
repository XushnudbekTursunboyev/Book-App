package uz.orign.bookapp.model.retrofit2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiBuilder {
    const val BASE_URL = "https://api.nytimes.com/svc/books/v3/"
    const val API_KEY = "cZOAHLd7L7Q1Gnrf8QERaztPpEIWSKyM"

    fun apiService(): ApiService {
        return getInstance().create(ApiService::class.java)
    }

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

}