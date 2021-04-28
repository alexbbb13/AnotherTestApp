package cvdevelopers.takehome.interactors

import com.google.gson.Gson
import cvdevelopers.takehome.models.Client
import cvdevelopers.takehome.models.ClientViewData
import cvdevelopers.takehome.models.db.ClientDb
import io.reactivex.Observable

class VisualsInteractorImpl(private val repository: VisualsRepository): VisualsInteractor {

    /*
    Business use cases

    When the app starts, check a if there is a list of cached users. If there is, return the cache list.
    If there is no users in cache, hit the endpoint, fetch the users, store them in a Database as Cache, and return the list as a result.
    Once the system gets a list of users, Display a list of the users in a recycler view.
    The Display should consist of the user display image as a circle followed by the users first and last name.
    If the app is force closed, upon open, the recycler view loads the data from the Database cache.
    If the user pulls the recycler view, the cache will be cleared and the system will hit the endpoint to get new data.
    If the device is rotated, the list is displayed in landscape mode and no new calls to fetch data are made.

     */

    override fun getAllVisuals(update: Boolean): Observable<List<ClientViewData>> {
            return when(update) {
                true -> repository.clearCache().flatMap {loadFromNetworkSaveTransform()}
                else -> repository.loadFromDb().flatMapObservable {
                    it -> when(it.isNullOrEmpty()) {
                    //no saved data in Db
                    true -> loadFromNetworkSaveTransform()
                    else -> dbToData(it)
                    }
                }
            }
    }

    private fun loadFromNetworkSaveTransform(): Observable<List<ClientViewData>>  {
        return repository
            .loadFromNetwork()
            .flatMap { list -> repository.saveToDb(list, dataToDb(list)) }
            .map { list -> dataToViewData(list) }
            .toObservable()
    }

    private fun dbToData(list: List<ClientDb>): Observable<List<ClientViewData>> {
        val mapped= list.map { item -> Gson().fromJson(item.clientdata, Client::class.java)}
        return Observable.just(dataToViewData(mapped))
    }

    private fun dataToDb(list: List<Client>): List<ClientDb> {
        return list.mapIndexed  {index, client ->
            ClientDb(
                index + 1,  //in production
                // we probably should get the Id from client, but there are
                // lots of duplicate fields in the ID server response
                // so for cache simple ids are OK, since the cache fetches the data
                // incrementing the ids
                Gson().toJson(client)
            )
        }
    }

    private fun dataToViewData(list: List<Client>): List<ClientViewData> {
        return list.map {client ->
            ClientViewData(
                client.id,
                client.name.first,
                client.name.last,
                client.picture.thumbnail
            )
        }
    }
}