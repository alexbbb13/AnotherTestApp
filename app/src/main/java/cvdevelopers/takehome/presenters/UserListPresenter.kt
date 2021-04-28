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
        interactor.getAllVisuals(update)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({data ->
                if(update) viewState.stopRefreshing()
                onNewDataLoaded(data)
            },{this::onError})
    }

    fun updateImages() {
        loadImages(true)
    }
}