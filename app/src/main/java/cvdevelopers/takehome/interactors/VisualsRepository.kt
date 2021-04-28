package cvdevelopers.takehome.interactors

import android.util.Log
import com.google.gson.Gson
import cvdevelopers.takehome.api.RandomUserApiEndpoint
import cvdevelopers.takehome.models.Client
import cvdevelopers.takehome.models.db.ClientDb
import cvdevelopers.takehome.room.AppRoomDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class VisualsRepository(private val api: RandomUserApiEndpoint,
                        private val db: AppRoomDatabase) {
    fun clearCache(): Observable<Unit> {
        return Observable.fromCallable( {db.getClientDao().deleteAll()})
    }

    fun loadFromNetwork(): Single<List<Client>> {
        return api.getClient("1", "15")
            .map { resp -> resp.results }
    }

    fun loadFromDb(): Single<List<ClientDb>> {
        return Single.fromCallable( {db.getClientDao().getClients()})
        //return db.getClientDao).flatMap { it.getClients() }
    }

    fun saveToDb(dataOut: List<Client>, dataSave: List<ClientDb>): Single<List<Client>> {
        return Single.fromCallable( {db.getClientDao().insertClients(dataSave)})
            .flatMap { _ -> Single.just(dataOut) }
//        return db.map(AppRoomDatabase::getClientDao)
//            .
//            .flatMapCompletable { Completable.fromAction {
//                it.insertClients(dataSave)
//                Log.d("doxxxtor", "save to db="+ Gson().toJson(dataSave))
//            } }
//            .to { _ -> Single.just(dataOut) }
    }
}
