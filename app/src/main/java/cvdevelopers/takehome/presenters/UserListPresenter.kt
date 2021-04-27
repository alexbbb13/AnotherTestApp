package cvdevelopers.takehome.presenters

import cvdevelopers.takehome.adapters.UserListAdapter
import cvdevelopers.takehome.interactors.VisualsInteractor
import cvdevelopers.takehome.models.ClientViewData
import cvdevelopers.takehome.ui.UserListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class UserListPresenter constructor(val interactor: VisualsInteractor) : MvpPresenter<UserListView>() {

    private fun onError(t: Throwable) {
        t.printStackTrace()
    }

    private fun onNewDataLoaded(data: List<ClientViewData>) {
        viewState.setData(UserListAdapter(data))
    }

    fun loadImages(update: Boolean = false) {
        //viewState.setProgressBarVisibility(true)
        interactor.getAllVisuals(update)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            //.doOnComplete { viewState.setProgressBarVisibility(false) }
            .subscribe(this::onNewDataLoaded, this::onError)
    }

    fun updateImages() {
        loadImages(true)
    }
}