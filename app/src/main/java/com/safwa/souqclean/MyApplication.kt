package com.safwa.souqclean

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.LocaleList
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork
import com.google.firebase.BuildConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.safwa.souqclean.data.prefrances.PreferenceDataStoreConstants
import com.safwa.souqclean.data.prefrances.PreferenceDataStoreHelper
import com.safwa.souqclean.utils.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rx.schedulers.Schedulers
import java.util.Locale


//دا الكلاس ال بيفتح اول م ارن الابلكيشن مباشره
//mange app lifecycle
//عشان كدا باعمل انشيالايز لكل ال باستخدمه فيه

class MyApplication :Application() {



    override fun onCreate() {

        initLanguage()
        super.onCreate()
        Logger.init(BuildConfig.DEBUG)
        FirebaseApp.initializeApp(this)
        listenToNetworkConnectivity()


    }

    private fun initLanguage() {
        CoroutineScope(Dispatchers.IO).launch {
            val preferenceHelper = PreferenceDataStoreHelper(this@MyApplication)
            val language = preferenceHelper.getPreference(PreferenceDataStoreConstants.LANGUAGE_KEY, "ar")
            setLocale(language.toString())
        }
    }

    private fun setLocale(languageCode: String) {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
    }




    private fun listenToNetworkConnectivity(){

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


/*
    private fun listenToNetworkConnectivity() {
        ReactiveNetwork.observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnDispose {
                Log.e(TAG, "Network observer disposed")
            }
            .subscribe(
                { isConnected: Boolean ->
                    Log.e(TAG, "Connection to internet is $isConnected")
                    FirebaseCrashlytics.getInstance().setCustomKey("connect_to_internet", isConnected)
                },
                { throwable ->
                    Log.e(TAG, "Error observing network connectivity", throwable)
                }
            )
    }
*/
    companion object{

        private const val TAG="MyApplication"

    }
}


