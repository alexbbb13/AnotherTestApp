package cvdevelopers.takehome.interactors

import cvdevelopers.takehome.models.ClientViewData
import io.reactivex.Observable

interface VisualsInteractor {
    fun getAllVisuals(update: Boolean): Observable<List<ClientViewData>>
}