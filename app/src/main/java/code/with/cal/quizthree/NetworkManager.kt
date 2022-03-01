package code.with.cal.quizthree

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkManager {

    private val UserokHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("Authorization", "Bearer bkjcsbcg687hwgjhgksc")
                .build()
            chain.proceed(request)
        }
        .build()

    private val userRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://papp.ir/api/v1/")
        .client(UserokHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val userService = userRetrofit.create(Services::class.java)



}