package parkar.alim.daggerwithkotlin.modules

import android.app.Activity
import android.content.Context

import dagger.Module
import dagger.Provides
import parkar.alim.daggerwithkotlin.annotations.ActivityContext

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return mActivity
    }

    @Provides
    fun provideActivity(): Activity {
        return mActivity
    }
}