package com.mindorks.framework.mvi.view

import com.mindorks.framework.mvi.mvibase.UserViewState
import io.reactivex.Observable

interface UserView {

    fun getDeatilsButtonClick(): Observable<Boolean>

    fun fetch(mainViewState: UserViewState)
}