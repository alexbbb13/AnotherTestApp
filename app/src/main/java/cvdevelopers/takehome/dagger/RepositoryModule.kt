package cvdevelopers.takehome.dagger

import cvdevelopers.takehome.api.RandomUserApiEndpoint
import cvdevelopers.takehome.interactors.VisualsInteractorImpl
import cvdevelopers.takehome.interactors.VisualsRepository
import cvdevelopers.takehome.room.AppRoomDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import javax.inject.Singleton

@Module
class RepositoryModule  {

    @Provides
    @Singleton
    fun provideVisualsRepository(api: RandomUserApiEndpoint, db: AppRoomDatabase):VisualsRepository = VisualsRepository(api, db)

}
