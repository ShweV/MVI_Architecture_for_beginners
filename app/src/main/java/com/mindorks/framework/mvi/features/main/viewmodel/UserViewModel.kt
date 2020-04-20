package com.mindorks.framework.mvi.features.main.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindorks.framework.mvi.data.api.ApiHelper
import com.mindorks.framework.mvi.data.api.ApiServiceImpl
import com.mindorks.framework.mvi.data.model.User
import com.mindorks.framework.mvi.data.repository.UserRepository
import com.mindorks.framework.mvi.features.main.main.statemodel.PartialMainViewState
import com.mindorks.framework.mvi.features.main.main.statemodel.UserInteractor
import com.mindorks.framework.mvi.features.main.main.statemodel.UserViewState
import com.mindorks.framework.mvi.features.main.main.view.UserView
import com.mindorks.framework.mvi.util.Resource
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class UserViewModel(private val mainInteractor: UserInteractor = UserInteractor()) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val users = MutableLiveData<Resource<List<User>>>()
    private var user_data:Single<List<User>> = TODO("Initialize Single<List<User>>")
    private val stateSubject = BehaviorSubject.create<PartialMainViewState>()

    fun bind(userView: UserView) {
        val dataChangeObservable = userView.onButtonClick()
            .flatMap {mainInteractor.fetchUserList().startWith(PartialMainViewState.ProgressState())}

        val mergedIntentsObservable = Observable.merge(listOf(dataChangeObservable)).subscribeWith(stateSubject)
        compositeDisposable.add(mergedIntentsObservable.scan(UserViewState(false,false, renderList(user_data)),
            this::reduce).subscribe { userView.fetch(it)})
    }

    fun unbind() {
        compositeDisposable.clear()
    }

    private fun reduce(previousState: UserViewState,partialState: PartialMainViewState): UserViewState {
        return when (partialState) {
            is PartialMainViewState.ProgressState -> UserViewState(progress = true)
            is PartialMainViewState.ErrorState -> UserViewState(error = false)
            is PartialMainViewState.ListFetchedState -> UserViewState(userList = renderList(user_data))
        }
    }

    fun fetchUsers() {
        val userRepository = UserRepository(ApiHelper(ApiServiceImpl()))
        users.postValue(Resource.loading(null))
        compositeDisposable.add(
            userRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    users.postValue(Resource.success(userList))
                }, {
                    users.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getUsers(): Single<List<User>> {
        return user_data
    }

    private fun renderList(users: Single<List<User>>) : List<User> {
        val user_data:List<User> = listOf()
        TODO("Iterate through Single<List<User> get user and add it to List<User>")
        return user_data
    }


}