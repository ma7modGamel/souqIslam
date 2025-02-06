package com.safwa.souqclean.data.repository.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.safwa.souqclean.data.datasource.local.prefrances.PreferenceDataStoreConstants
import com.safwa.souqclean.data.datasource.local.prefrances.PreferenceDataStoreConstants.IS_USER_LOGGED_IN
import com.safwa.souqclean.data.datasource.local.prefrances.dataStore

import com.safwa.souqclean.data.models.auth.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

//==================================================================================

//متى تستخدم كل منهما؟
//✅ استخدم Flow إذا كنت بحاجة إلى:
//
//الحصول على تدفق بيانات يتم تنفيذه عند كل اشتراك جديد (مثل استدعاء API أو جلب بيانات من قاعدة بيانات).
//تنفيذ الكود فقط عندما يكون هناك مشترك للحصول على الأداء الأفضل.

//==================================================================================


class UserPreferenceRepositoryImpl(private val context: Context) : IUserPreferenceRepository {

    val isUserLoggedIn: Flow<Boolean> =
        context.dataStore.data.map { preferencesDataStore ->
            preferencesDataStore[IS_USER_LOGGED_IN] ?: false
        }

    override suspend fun isUserLoggedIn(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun saveUserLoggedInStatus(isLoggedIn: Boolean) {
        context.dataStore.edit { preferencesDataStore ->
            preferencesDataStore[IS_USER_LOGGED_IN] = isLoggedIn
        }
    }

    override suspend fun getUserData(): UserData? {
        TODO("Not yet implemented")
    }

    override suspend fun saveUserData(userData: UserData) {
        TODO("Not yet implemented")
    }

}
//==================================================================================

//✅ استخدم StateFlow إذا كنت بحاجة إلى:
//
//الاحتفاظ بالقيمة الأخيرة بحيث يحصل المشترك الجديد عليها مباشرة.
//التعامل مع الحالة داخل ViewModel أو الاحتفاظ بقيم الحالة عبر الشاشة.


//==================================================================================


//class UserPreferencesRepository(private val dataStoreHelper: PreferenceDataStoreHelper) {
//
//    val isUserLoggedIn: StateFlow<Boolean> = flow {
//        emit(dataStoreHelper.getFirstPreference(PreferenceDataStoreConstants.IS_USER_LOGGED_IN, false))
//    }.stateIn(
//        scope = CoroutineScope(Dispatchers.IO),
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = false
//    )
//
//    suspend fun setUserLoggedIn(isLoggedIn: Boolean) {
//        dataStoreHelper.putPreference(PreferenceDataStoreConstants.IS_USER_LOGGED_IN, isLoggedIn)
//    }
//}