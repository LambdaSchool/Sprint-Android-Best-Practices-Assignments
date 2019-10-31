package com.example.rxjava

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.math.pow

interface QuantumRandomApi{

    @GET("jsonI.php")
    fun getQuantumNumbers(@Query("length")length: Int = 2, @Query("type")type: String = "uint16") : Single<RandomNumbers>
}

class MainActivity : AppCompatActivity() {

    companion object{
        val BASE_URL = "https://qrng.anu.edu.au/API/"
    }

    lateinit var disposable: Disposable
    lateinit var disposable2: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val obsFirst = et_purchase_price.textChanges()
        val obsSecond = et_downpayment.textChanges()
        val obsThird = et_loan_length.textChanges()
        val obsFourth = et_interest_rate.textChanges()

        val obsCompared = Observables.combineLatest(obsFirst, obsSecond, obsThird, obsFourth){
            pp, dp, ll, it->
            try{
                val purchasePower = pp.toString().toDouble()
                val downPayment = dp.toString().toDouble()
                val loanLength = ll.toString().toDouble()
                val interestRate = (it.toString().toDouble()/100)

                "${String.format("%.1f", ((purchasePower*(1+interestRate).pow(loanLength))/((1+interestRate).pow(loanLength)-1))-downPayment).toDouble()}"
            } catch(n: NumberFormatException){
                ""
            }
        }

        disposable = obsCompared.subscribeOn(AndroidSchedulers.mainThread())
            .subscribe{
                tv_answer.text = it}


        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val service = retrofit.create(QuantumRandomApi::class.java)

        disposable2 = service.getQuantumNumbers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ randomNumbers ->
                if (randomNumbers.data[0] > randomNumbers.data[1]) {
                    tv_higher_mortgage_fixed_rate.setText("${randomNumbers.data[0]}")
                    tv_lower_mortgage_fixed_rate.setText("${randomNumbers.data[1]}")
                } else {
                    tv_higher_mortgage_fixed_rate.setText("${randomNumbers.data[1]}")
                    tv_lower_mortgage_fixed_rate.setText("${randomNumbers.data[0]}")
                }
            }, {
                tv_higher_mortgage_fixed_rate.setText("unknown")
                tv_lower_mortgage_fixed_rate.setText("unknown")
                throw it
            })
    }

    override fun onDestroy() {
        disposable.dispose()
        disposable2.dispose()
        super.onDestroy()
    }
}

data class RandomNumbers(val data: MutableList<Int>)
