package com.example.mortgagerates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    //todo 1
    lateinit var disposable: Disposable


    private lateinit var retrofitDisposable: Disposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setBindingDisposable()

        setRetroDisposable()

        btn_1.setOnClickListener {
            vTextInterestRate.setText(btn_1.text)
        }
        btn_2.setOnClickListener {
            vTextInterestRate.setText(btn_2.text)
        }
        btn_generate.setOnClickListener {

            progress_bar.visibility = View.VISIBLE

            setRetroDisposable()

        }
        buttonAdd.setOnClickListener {
            
        }


    }
    override fun onDestroy() {

        retrofitDisposable.dispose()

        disposable.dispose()

        super.onDestroy()

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
            textView5.text = result.toString()
        }
    }

    fun setRetroDisposable(){

        retrofitDisposable = Api.InitializeRetro.startRetroCalls().getNum(2, "uint8")

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn { Numbers(arrayOf(1, 2)) }
            .subscribe { randomNums ->
                for(i in randomNums.data.indices) {
                    when(i){
                        0 ->{
                            if (randomNums.data[i] > 99){
                                btn_1.text = (randomNums.data[i]/10000.0).toString()
                            }else if (randomNums.data[i] in 10..99){
                                btn_1.text = (randomNums.data[i]/1000.0).toString()
                            }else{
                                btn_1.text = (randomNums.data[i]/100.0).toString()
                            }
                        }
                        1 ->{
                            if (randomNums.data[i] > 99){
                                btn_2.text = (randomNums.data[i]/10000.0).toString()
                            }else if (randomNums.data[i] in 10..99){
                                btn_2.text = (randomNums.data[i]/1000.0).toString()
                            }else{
                                btn_2.text = (randomNums.data[i]/100.0).toString()
                            }
                        }
                    }
                }
                progress_bar.visibility = View.INVISIBLE
            }
    }
}





