package com.example.mortgagerates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    //todo 1
    lateinit var disposable: Disposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setBindingDisposable()


    }

    fun getAmortizedMortgagePayment(): Double {

        val amount = vTextValue.text.toString()
        val payment = vTextDown.text.toString()
        val rate = vTextInterestRate.text.toString()
        val period = vTextTerm.text.toString()
        return if (amount.isEmpty() ||
                    payment.isEmpty() ||
                    rate.isEmpty() ||
                    period.isEmpty()
        ) {

            0.0

        } else {
            val interestPerMonth = rate.toDouble()/12
            val payPeriods = period.toInt()
            val loanAmount = amount.toDouble() - payment.toDouble()

            val top: Double = (1+interestPerMonth).pow(payPeriods)*interestPerMonth*loanAmount
            val bot: Double = (1+interestPerMonth).pow(payPeriods)-1
            top/bot
        }
    }

    fun setBindingDisposable() {
        val obsAmount = vTextValue.textChanges()
            .filter { it.length > 1 }
        val obsPayment = vTextDown.textChanges()
            .filter { it.length > 1 }
        val obsRate = vTextInterestRate.textChanges()
            .filter { it.length > 1 }
        val obsPeriod = vTextTerm.textChanges()
            .filter { it.length > 1 }

        val combined = Observables.combineLatest(
            obsAmount, obsPayment,
            obsRate, obsPeriod
        ) { obsAmount, obsPayment, obsRate, obsPeriod ->
            getAmortizedMortgagePayment()
        }
        disposable = combined.observeOn(AndroidSchedulers.mainThread()).subscribe{result ->
            vTextCalculate.text = result.toString()

        }
    }
}





