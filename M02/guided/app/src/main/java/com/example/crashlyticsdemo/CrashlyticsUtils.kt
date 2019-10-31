package com.example.crashlyticsdemo

import com.crashlytics.android.Crashlytics

fun dropBreadCrubbs(className: String, MethodName: String, id: Long, generalData: String){

    val breadCrumb ="$className - $MethodName - $id - $generalData"


    Crashlytics.log(breadCrumb)
}