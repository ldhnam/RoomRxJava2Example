package io.github.ldhnam.roomrxjava2.data.remote

import io.github.ldhnam.roomrxjava2.data.model.User
import io.reactivex.Single
import retrofit2.http.GET


interface RestService {

    @GET("/api/?ext&region=United States&amount=10")
    fun getUsers(): Single<ArrayList<User>>
}