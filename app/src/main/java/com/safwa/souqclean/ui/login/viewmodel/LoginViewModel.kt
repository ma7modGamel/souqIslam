package com.safwa.souqclean.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import com.safwa.souqclean.data.repository.user.UserPreferenceRepositoryImpl

class LoginViewModel(
    private val userPreferenceRepositoryImpl: UserPreferenceRepositoryImpl
    ):ViewModel() {
    // TODO: Implement the ViewModel

    fun getUserLoggedInStatus() = userPreferenceRepositoryImpl.isUserLoggedIn
    

}