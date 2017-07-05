package parkar.alim.daggerwithkotlin.components

import dagger.Component
import parkar.alim.daggerwithkotlin.annotations.PerActivity
import parkar.alim.daggerwithkotlin.modules.ActivityModule
import parkar.alim.daggerwithkotlin.ui.LauncherActivity

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: LauncherActivity)
}