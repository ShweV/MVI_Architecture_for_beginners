package com.mindorks.framework.mvi.features.main.main.statemodel

import com.mindorks.framework.mvi.data.model.User

sealed class PartialMainViewState {
    class ProgressState : PartialMainViewState()

    class ErrorState : PartialMainViewState()

    class ListFetchedState(val userList: List<User>) : PartialMainViewState()

}