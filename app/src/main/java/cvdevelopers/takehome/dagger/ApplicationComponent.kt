package cvdevelopers.takehome.dagger

import cvdevelopers.takehome.MainActivity
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import dagger.Component
import javax.inject.Singleton

/**
 * Created by CamiloVega on 10/7/17.
 */
@Singleton
@Component(modules = arrayOf(
    ApplicationModule::class,
    NetworkClientModule::class,
    DbModule::class))
interface ApplicationComponent {
    fun inject(app: LuminaryTakeHomeApplication)
    fun inject(target: MainActivity)
}