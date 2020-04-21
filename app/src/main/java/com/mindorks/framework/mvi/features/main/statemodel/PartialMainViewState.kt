package com.mindorks.framework.mvi.features.main.main.statemodel

import com.mindorks.framework.mvi.data.model.User
import io.reactivex.Single

sealed class PartialMainViewState {
    class ProgressState : PartialMainViewState()

    class ErrorState : PartialMainViewState()

    class ListFetchedState(val userList: Single<List<User>>) : PartialMainViewState()
}
