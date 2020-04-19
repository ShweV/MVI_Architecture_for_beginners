package com.mindorks.framework.mvi.features.main.main.viewmodel

import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvi.features.main.main.statemodel.PartialMainViewState
import com.mindorks.framework.mvi.features.main.main.statemodel.UserInteractor
import com.mindorks.framework.mvi.features.main.main.statemodel.UserViewState
import com.mindorks.framework.mvi.features.main.main.view.UserView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject

class UserViewModel(private val mainInteractor: UserInteractor = UserInteractor()) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val stateSubject = BehaviorSubject.create<PartialMainViewState>()

    fun bind(mainView: UserView) {
        val dataChangeObservable = mainView.onButtonClick()
            .flatMap {mainInteractor.fetchUserList().startWith(PartialMainViewState.ProgressState())}

        val mergedIntentsObservable = Observable.merge(listOf(dataChangeObservable)).subscribeWith(stateSubject)
        compositeDisposable.add(mergedIntentsObservable.scan(UserViewState(), this::reduce).subscribe { mainView.fetch(it) })
    }

    fun unbind() {
        compositeDisposable.clear()
    }

    fun getUsers(mainView:UserView) {
        val dataChangeObservable = mainView.onButtonClick()
            .flatMap {mainInteractor.getUsersRealState().startWith(PartialMainViewState.ProgressState())}

        val mergedIntentsObservable = Observable.merge(listOf(dataChangeObservable)).subscribeWith(stateSubject)
        compositeDisposable.add(mergedIntentsObservable.scan(UserViewState(), this::reduce).subscribe { mainView.fetch(it) })
    }

    private fun reduce(previousState: UserViewState,partialState: PartialMainViewState): UserViewState {
        return when (partialState) {
            is PartialMainViewState.ProgressState -> UserViewState(progress = true)
            is PartialMainViewState.ErrorState -> UserViewState(error = true)
            is PartialMainViewState.ListFetchedState -> UserViewState(userList = partialState.userList)
        }
    }
}