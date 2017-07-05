package parkar.alim.daggerwithkotlin.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import dagger.Module
import dagger.Provides
import parkar.alim.daggerwithkotlin.annotations.ApplicationContext
import parkar.alim.daggerwithkotlin.annotations.DatabaseInfo

@Module
class ApplicationModule(private val mApplication: Application) {

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApplication
    }

    @Provides
    internal fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseName(): String {
        return "demo-dagger.db"
    }

    @Provides
    @DatabaseInfo
    internal fun provideDatabaseVersion(): Int {
        return 2
    }

    @Provides
    internal fun provideSharedPrefs(): SharedPreferences {
        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE)
    }
}