package parkar.alim.daggerwithkotlin

import android.app.Application
import android.content.Context

import javax.inject.Inject

import parkar.alim.daggerwithkotlin.components.ApplicationComponent
import parkar.alim.daggerwithkotlin.managers.DataManager
import parkar.alim.daggerwithkotlin.modules.ApplicationModule

class KotlinDaggerApplication : Application() {

    var component: ApplicationComponent
        protected set

    @Inject
    internal var dataManager: DataManager? = null

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        component.inject(this)
    }

    companion object {

        operator fun get(context: Context): KotlinDaggerApplication {
            return context.applicationContext as KotlinDaggerApplication
        }
    }
}