package cvdevelopers.takehome.dagger

import android.content.Context
import androidx.room.Room
import cvdevelopers.takehome.room.AppRoomDatabase
import dagger.Lazy
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import javax.inject.Singleton

@Module
class DbModule  {

    @Provides
    @Singleton
    fun provideAppRoom(context: Context): AppRoomDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppRoomDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration() // Cleans the database without the migrations
            .build()
    }

    @Provides
    @Singleton
    fun provideAppRoomSingle(appRoomLazy: Lazy<AppRoomDatabase>): Single<AppRoomDatabase> {
        return Single.fromCallable { appRoomLazy.get() }
    }
}
