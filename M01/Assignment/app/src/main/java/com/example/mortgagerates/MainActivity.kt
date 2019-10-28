package com.example.mortgagerates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //todo 1
    lateinit var disposable: Disposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val obsValue = vTextValue.textChanges()
            .filter { it.length > 1 }
        val obsDown = vTextDown.textChanges()
            .filter { it.length > 1 }

        val obsValueAndDown = Observables.combineLatest(obsValue, obsDown) { x, y ->

            val newValueX = x.toString().toInt()
            val newValueY = y.toString().toInt()
            val newValue = newValueX - newValueY
            val calc = newValue

            val format = newValue.toString().split(".")
            val print = format[0]

            "$$print"

        }
            /*    disposable = combined.observeOn(AndroidSchedulers.mainThread()).subscribe {calc -> display.text = calc}*/
            //disposable = obsValueAndDown.subscribe{
            //   vTextNewValue.text = it
            disposable =
                obsValueAndDown.observeOn(AndroidSchedulers.mainThread()).subscribe { newValue ->
                    vTextNewValue.text = newValue
                }
        }
    }

