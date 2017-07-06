package parkar.alim.daggerwithkotlin

import android.app.Application
import android.content.Context

import javax.inject.Inject

import parkar.alim.daggerwithkotlin.components.ApplicationComponent
import parkar.alim.daggerwithkotlin.components.DaggerApplicationComponent
import parkar.alim.daggerwithkotlin.managers.DataManager
import parkar.alim.daggerwithkotlin.modules.ApplicationModule

class KotlinDaggerApplication : Application() {

    lateinit var component: ApplicationComponent

    @Inject
    lateinit public var dataManager: DataManager

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        component.inject(this)
    }

    //public static methods in java
    companion object {

        operator fun get(context: Context): KotlinDaggerApplication {
            return context.applicationContext as KotlinDaggerApplication
        }
    }
}