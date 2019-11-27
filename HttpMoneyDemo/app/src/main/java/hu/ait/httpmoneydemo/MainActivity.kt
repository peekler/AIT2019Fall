package hu.ait.httpmoneydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import hu.ait.httpmoneydemo.api.MoneyAPI
import hu.ait.httpmoneydemo.data.MoneyResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val currencyAPI = retrofit.create(MoneyAPI::class.java)

        btnRates.setOnClickListener {
            tvResult.visibility = View.INVISIBLE

            val ratesCall= currencyAPI.getMoney("USD")

            ratesCall.enqueue(object: Callback<MoneyResult>{
                override fun onFailure(call: Call<MoneyResult>, t: Throwable) {
                    tvResult.text = t.message
                }

                override fun onResponse(call: Call<MoneyResult>, response: Response<MoneyResult>) {
                    var moneyResult = response.body()
                    tvResult.text = "${moneyResult!!.rates!!.HUF}"

                    revealMoneyLayout()
                }
            })
        }
    }


    fun revealMoneyLayout() {
        val x = tvResult.getRight()
        val y = tvResult.getBottom()

        val startRadius = 0
        val endRadius = Math.hypot(tvResult.getWidth().toDouble(),
            tvResult.getHeight().toDouble()).toInt()

        val anim = ViewAnimationUtils.createCircularReveal(
            tvResult,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )

        tvResult.setVisibility(View.VISIBLE)
        anim.start()
    }


}
