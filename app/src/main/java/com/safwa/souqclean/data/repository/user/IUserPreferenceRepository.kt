package com.safwa.souqclean.data.repository.user

import com.safwa.souqclean.data.models.auth.UserData
import kotlinx.coroutines.flow.Flow

interface IUserPreferenceRepository {
    suspend fun isUserLoggedIn(): Flow<Boolean>
    suspend fun saveUserLoggedInStatus(isLoggedIn:Boolean)

    suspend fun getUserData(): Flow<UserData?>
    suspend fun saveUserData(userData: UserData)

}