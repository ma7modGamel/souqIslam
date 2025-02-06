package com.safwa.souqclean.data.repository.user

import com.safwa.souqclean.data.models.auth.UserData

interface IUserPreferenceRepository {
    suspend fun isUserLoggedIn(): Boolean
    suspend fun saveUserLoggedInStatus(isLoggedIn: Boolean)
    suspend fun getUserData(): UserData?
    suspend fun saveUserData(userData: UserData)
}