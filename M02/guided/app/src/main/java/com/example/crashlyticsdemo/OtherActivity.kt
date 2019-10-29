package com.example.crashlyticsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_other.*

class OtherActivity : AppCompatActivity() {
    companion object {
        // Source: https://www.iihs.org/about-us
        val iihsFacts = arrayOf(
            "1959: Three insurance industry groups representing more than 500 auto insurers establish IIHS to funnel support to academic and other organizations in the field of highway safety.",
            "1969: IIHS launches bumper tests, which lead to the first federal bumper standard.",
            "1972: The Highway Loss Data Institute (HLDI) is organized as an affiliate of IIHS to collect, analyze and publish insurance loss information on most car, SUV, pickup truck and motorcycle models on U.S. roads.",
            "1974: IIHS evaluates the consequences of lowering the legal minimum age for purchasing alcohol. Findings provide the scientific basis for enacting 21 minimum age laws in all states.",
            "1976: IIHS crash tests demonstrate airbag effectiveness in frontal crashes. Highlights are shown before a U.S. congressional committee.",
            "1979: IIHS research on signal timing leads traffic engineers to lengthen yellow light intervals.",
            "1980: IIHS makes its first appearance on primetime television in \"60 Minutes\" segment on Jeep rollover dangers.",
            "1983: The Supreme Court rules in favor of insurers' efforts to get airbags in cars.",
            "1989: IIHS publishes the first consumer information that compares death rates among drivers by vehicle make and model.",
            "1990: Two 1989 Chrysler LeBarons collide head-on in rural Virginia. Both drivers walk away with minor injuries. The crash, believed to be the first of its kind involving two airbag-equipped vehicles, vindicates IIHS and other safety advocates who lobbied for decades for an airbag requirement, which had just been enacted.",
            "1993: IIHS reveals that daylight saving time reduces crash deaths, a finding that contributes to the subsequent designation of more weeks clock-forward.",
            "1996: Years of IIHS research on teen drivers pay off when the nation's first three-stage graduated licensing law goes into effect in Florida.",
            "2005: IIHS finds huge benefits of electronic stability control in reducing serious crashes, accelerating introduction of this feature ahead of federal requirements.",
            "2008: IIHS is first to compare booster seats and rate their potential effectiveness based on how well they improve the fit of adult lap and shoulder belts for children too old for child restraints but too young for belts alone.",
            "2010: Contrary to expectations, IIHS study finds that older drivers aren't dying more often in crashes, even though they're holding onto their licenses longer.",
            "2013: IIHS launches its first test program of an advanced crash avoidance technology, rating vehicles for front crash prevention.",
            "2016: IIHS launches headlight ratings, filling a void in information about this basic equipment.",
            "2019: IIHS launches ratings of automatic emergency braking systems that recognize and stop for pedestrians."
        )
    }

    //variable to call the index of array
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        dropBreadCrubbs("OtherActivity", "onCreate", 0L, "In the OtherActivity")

    buttonNextFact.setOnClickListener {
        dropBreadCrubbs("buttonNextFactListener", "onClick", index.toLong(), "Going through the array list")
        textViewFacts.text = iihsFacts[index]

        index++
    }


    }
}
