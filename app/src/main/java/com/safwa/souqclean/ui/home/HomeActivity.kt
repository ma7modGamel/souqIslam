package com.safwa.souqclean.ui.home

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.safwa.souqclean.R
import com.safwa.souqclean.data.datasource.local.prefrances.PreferenceDataStoreConstants
import com.safwa.souqclean.data.datasource.local.prefrances.PreferenceDataStoreHelper
import com.safwa.souqclean.ui.login.AuthActivity
import com.safwa.souqclean.utils.CrashlyticsUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeActivity :Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (checkUser()){

        }else{

            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)

        }

    CrashlyticsUtils.sendLogToCrashlytics("OpenCrash","button","key",",crash")


    }

    private fun  checkUser() :Boolean {

        CoroutineScope(Dispatchers.IO).launch {
            PreferenceDataStoreHelper(baseContext).getPreference(
                PreferenceDataStoreConstants.IS_USER_LOGGED_IN,
                false
            )
        }
        return false
    }

    private fun initSplashScreen() {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.S) {
            installSplashScreen()
            setupAnimation()
        }else{
            setTheme(R.style.Theme_SouqClean)
        }

    }

    private fun setupAnimation() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

            splashScreen.setOnExitAnimationListener { splashView->

                val slideUp=ObjectAnimator.ofFloat(
                    splashView,View.TRANSLATION_Y,0f,-splashView.height.toFloat()
                )
                slideUp.interpolator=AnticipateInterpolator()
                slideUp.duration=3000L
                slideUp.doOnEnd { splashView.remove() }

                slideUp.start()
            }


        }


    }

}