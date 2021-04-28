package cvdevelopers.takehome.dagger

import cvdevelopers.takehome.interactors.VisualsInteractor
import cvdevelopers.takehome.presenters.UserListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule  {

    @Provides
    @Singleton
    fun provideUserListPresenter(interactor: VisualsInteractor) = UserListPresenter(interactor)

}
