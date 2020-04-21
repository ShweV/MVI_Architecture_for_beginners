package com.mindorks.framework.mvi.features.main.main.statemodel

import com.mindorks.framework.mvi.data.api.ApiHelper
import com.mindorks.framework.mvi.data.api.ApiServiceImpl
import com.mindorks.framework.mvi.data.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class UserInteractor {
    var error = false

    fun fetchUserList(): Observable<PartialMainViewState> {
        val userRepository = UserRepository(ApiHelper(ApiServiceImpl()))
        return Observable
            .timer(2000, TimeUnit.MILLISECONDS)
            .map {
                return@map if (error) {
                    error = false
                    PartialMainViewState.ErrorState()
                } else
                    PartialMainViewState.ListFetchedState(userRepository.getUsers())
            }.observeOn(AndroidSchedulers.mainThread())
    }
}
