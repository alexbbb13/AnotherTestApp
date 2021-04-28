package cvdevelopers.takehome

import android.app.Application
import com.facebook.stetho.Stetho
import cvdevelopers.takehome.dagger.ApplicationComponent
import cvdevelopers.takehome.dagger.ApplicationModule
import cvdevelopers.takehome.dagger.DaggerApplicationComponent

class LuminaryTakeHomeApplication : Application() {

    val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this);
        Stetho.initializeWithDefaults(this)
    }

}