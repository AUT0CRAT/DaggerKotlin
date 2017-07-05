package parkar.alim.daggerwithkotlin.components


import android.app.Application
import android.content.Context
import dagger.Component
import parkar.alim.daggerwithkotlin.KotlinDaggerApplication
import parkar.alim.daggerwithkotlin.annotations.ApplicationContext
import parkar.alim.daggerwithkotlin.database.DBHelper
import parkar.alim.daggerwithkotlin.managers.DataManager
import parkar.alim.daggerwithkotlin.modules.ApplicationModule
import parkar.alim.daggerwithkotlin.preferences.PreferenceHelper
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(demoApplication: KotlinDaggerApplication)

    @ApplicationContext
    val context: Context

    val application: Application

    val dataManager: DataManager

    val preferenceHelper: PreferenceHelper

    val dbHelper: DBHelper

}