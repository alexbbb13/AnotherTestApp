package cvdevelopers.takehome.dagger

import android.content.Context
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cvdevelopers.takehome.api.RandomUserApiEndpoint
import cvdevelopers.takehome.room.AppRoomDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
}
