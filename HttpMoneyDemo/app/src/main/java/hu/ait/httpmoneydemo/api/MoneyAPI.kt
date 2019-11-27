package hu.ait.httpmoneydemo.api

import hu.ait.httpmoneydemo.data.MoneyResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// host: https://api.exchangeratesapi.io
// path: /latest
// query params: ? base=USD

interface MoneyAPI {
    @GET("/latest")
    fun getMoney(@Query("base") base: String) : Call<MoneyResult>
}