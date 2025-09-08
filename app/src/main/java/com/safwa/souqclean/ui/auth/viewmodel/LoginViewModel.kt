package com.safwa.souqclean.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import com.safwa.souqclean.data.repository.user.UserPreferenceRepositoryImpl
import kotlinx.coroutines.flow.Flow

class LoginViewModel(private val userPreferenceRepositoryImpl: UserPreferenceRepositoryImpl):ViewModel() {
    // TODO: Implement the ViewModel

    suspend fun getUserLoggedInStatus(): Flow<Boolean> {
         return userPreferenceRepositoryImpl.isUserLoggedIn()
    }
}