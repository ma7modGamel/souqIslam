package com.safwa.souqclean

import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import android.widget.Button
import android.window.SplashScreen
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.safwa.souqclean.utils.CrashlyticsUtils

class MainActivity :Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        initSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    CrashlyticsUtils.sendLogToCrashlytics("OpenCrash","button","key",",crash")


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