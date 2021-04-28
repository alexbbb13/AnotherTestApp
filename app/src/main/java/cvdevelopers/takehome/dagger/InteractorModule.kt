package cvdevelopers.takehome.dagger

import cvdevelopers.takehome.interactors.VisualsInteractor
import cvdevelopers.takehome.interactors.VisualsInteractorImpl
import cvdevelopers.takehome.interactors.VisualsRepository
import dagger.Module
import dagger.Provides

@Module
class InteractorModule  {

    @Provides
    fun provideVisualsInteractor(visualsRepository: VisualsRepository):VisualsInteractor = VisualsInteractorImpl(visualsRepository)

}
