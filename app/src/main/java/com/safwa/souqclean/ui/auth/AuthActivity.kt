package com.safwa.souqclean.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.safwa.souqclean.R
import com.safwa.souqclean.ui.auth.fragments.LoginFragment

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }


    }
}