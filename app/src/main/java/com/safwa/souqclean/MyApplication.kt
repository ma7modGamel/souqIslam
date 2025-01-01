package com.safwa.souqclean

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log

import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import rx.schedulers.Schedulers


//دا الكلاس ال بيفتح اول م ارن الابلكيشن مباشره
//mange app lifecycle
//عشان كدا باعمل انشيالايز لكل ال باستخدمه فيه

class MyApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        listenToNetworkConnectivity()


    }



    fun listenToNetworkConnectivity(){

//        val receiver = ComponentName(this,DeviceBootReciver::class.java)
//        packageManager.setComponentEnabledSettings(receiver,
//            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//            PackageManager.DONT_KILL_APP
//        )



        ReactiveNetwork.observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
        // anything else what you can do with RxJava
            .observeOn(Schedulers.io())
            .subscribe { isConnected: Boolean ->

                Log.e(TAG,isConnected.toString())
                Log.e(TAG,"Connection to internet is $isConnected")
                FirebaseCrashlytics.getInstance().setCustomKey("connect_to_internet",isConnected)

            }

    }


    companion object{

        private const val TAG="MyApplication"

    }
}


