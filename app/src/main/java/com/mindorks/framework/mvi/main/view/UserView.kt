package com.mindorks.framework.mvi.main.view

import com.mindorks.framework.mvi.main.statemodel.UserViewState
import io.reactivex.Observable

interface UserView {
    fun onButtonClick(): Observable<Boolean>
    fun fetch(mainViewState: UserViewState)
}