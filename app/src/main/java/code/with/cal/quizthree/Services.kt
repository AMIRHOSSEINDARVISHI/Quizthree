package code.with.cal.quizthree

import code.with.cal.quizthree.CreateUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Services {
    @POST("users")
    fun createUser(@Body body: CreateUser): Call<String>

}