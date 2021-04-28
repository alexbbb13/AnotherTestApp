package cvdevelopers.takehome.ui

import android.content.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import cvdevelopers.githubstalker.R
import cvdevelopers.takehome.LuminaryTakeHomeApplication
import cvdevelopers.takehome.adapters.UserListAdapter
import cvdevelopers.takehome.presenters.UserListPresenter
import dagger.android.support.AndroidSupportInjection
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class UserListFragment : MvpAppCompatFragment(), UserListView {

    @Inject
    lateinit var presenterProvider: Provider<UserListPresenter>

    @InjectPresenter
    lateinit var presenter: UserListPresenter

    @ProvidePresenter
    fun providePresenter(): UserListPresenter {
        val presenter = presenterProvider.get()
        return presenter
    }

    @BindView(R.id.swipe)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    @BindView(R.id.rv_user_list)
    lateinit var recyclerView: RecyclerView

    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as LuminaryTakeHomeApplication).appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_user_list, container, false)
        ButterKnife.bind(this, root)

        viewManager = LinearLayoutManager(context)
        swipeRefreshLayout.setOnRefreshListener { presenter.updateImages() }
        presenter.loadImages()
        return root
    }

    override fun setData(adapterWithData: UserListAdapter) {
        this.apply {
            activity?.let {
                recyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = viewManager
                    adapter = adapterWithData
                }
            }
        }
    }

    override fun stopRefreshing() {
       swipeRefreshLayout.isRefreshing = false
    }
}

