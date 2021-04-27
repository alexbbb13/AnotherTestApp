package cvdevelopers.takehome.ui

import cvdevelopers.takehome.adapters.UserListAdapter
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface UserListView:MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun setData(adapter: UserListAdapter)
}
