package cvdevelopers.takehome.ui

import cvdevelopers.takehome.adapters.UserListAdapter
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface UserListView:MvpView {
    fun setData(adapter: UserListAdapter)
    fun stopRefreshing()
}
