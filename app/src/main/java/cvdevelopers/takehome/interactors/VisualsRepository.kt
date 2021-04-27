package cvdevelopers.takehome.interactors

import cvdevelopers.takehome.api.RandomUserApiEndpoint
import cvdevelopers.takehome.models.Client
import cvdevelopers.takehome.models.db.ClientDb
import cvdevelopers.takehome.room.AppRoomDatabase
import io.reactivex.Completable
import io.reactivex.Single

class VisualsRepository(private val api: RandomUserApiEndpoint,
                        private val db: Single<AppRoomDatabase>) {
    fun clearCache(): Completable {
        return db.map(AppRoomDatabase::getClientDao)
            .flatMapCompletable { Completable.fromAction { it.deleteAll() } }
    }

    fun loadFromNetwork(): Single<List<Client>> {
        return api.getClient("1", "15")
            .map { resp -> resp.results }
    }

    fun loadFromDb(): Single<List<ClientDb>> {
        return db.map(AppRoomDatabase::getClientDao).flatMap { it.getClients() }
    }

    fun saveToDb(data: List<ClientDb>): Completable {
        return db.map(AppRoomDatabase::getClientDao)
            .flatMapCompletable { Completable.fromAction { it.insertClients(data) } }
    }
}
