package cvdevelopers.takehome.dagger

import cvdevelopers.takehome.MainActivity
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.ui.UserListFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by CamiloVega on 10/7/17.
 */
@Singleton
@Component(modules = arrayOf(
    ApplicationModule::class,
    NetworkClientModule::class,
    DbModule::class,
    InteractorModule::class,
    RepositoryModule::class,
    PresenterModule::class
    ))
interface ApplicationComponent {
    fun inject(app: LuminaryTakeHomeApplication)
    fun inject(target: MainActivity)
    fun inject(target: UserListFragment)
}