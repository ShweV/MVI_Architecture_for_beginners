package com.mindorks.framework.mvi.features.main.main.view

import com.mindorks.framework.mvi.features.main.main.statemodel.UserViewState
import io.reactivex.Observable

interface UserView {
    fun onButtonClick(): Observable<Boolean>
    fun fetch(mainViewState: UserViewState)
}