package parkar.alim.daggerwithkotlin.managers

import android.content.Context
import android.content.res.Resources
import parkar.alim.daggerwithkotlin.annotations.ApplicationContext
import parkar.alim.daggerwithkotlin.database.DBHelper
import parkar.alim.daggerwithkotlin.models.User
import parkar.alim.daggerwithkotlin.preferences.PreferenceHelper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(@param:ApplicationContext private val mContext: Context,
            private val mDbHelper: DBHelper,
            private val mSharedPrefsHelper: PreferenceHelper) {

    fun saveAccessToken(accessToken: String) {
        mSharedPrefsHelper.put(PreferenceHelper.PREF_KEY_ACCESS_TOKEN, accessToken)
    }

    val accessToken: String?
        get() = mSharedPrefsHelper.get(PreferenceHelper.PREF_KEY_ACCESS_TOKEN, null)

    @Throws(Exception::class)
    fun createUser(user: User): Long? {
        return mDbHelper.insertUser(user)
    }

    @Throws(Resources.NotFoundException::class, NullPointerException::class)
    fun getUser(userId: Long): User? {
        return mDbHelper.getUser(userId)
    }
}