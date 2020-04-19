package com.mindorks.framework.mvi.features.main.main.statemodel

import com.mindorks.framework.mvi.data.api.ApiHelper
import com.mindorks.framework.mvi.data.api.ApiServiceImpl
import com.mindorks.framework.mvi.data.model.User
import com.mindorks.framework.mvi.data.repository.UserRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class UserInteractor {
    var error = false

    fun fetchUserList(): Observable<PartialMainViewState> {
        return Observable
            .timer(2000, TimeUnit.MILLISECONDS)
            .map {
                return@map if (error) {
                    error = false
                    PartialMainViewState.ErrorState()
                } else
                    PartialMainViewState.ListFetchedState(
                        listOf(
                            User(
                                "Dr. Edwin Orn",
                                "http://s3.amazonaws.com/uifaces/faces/twitter/sunlandictwin/128.jpg",
                                "Connor.Hartmann71@gmail.com"
                            ),
                            User(
                                "Alford Hoeger",
                                "http://s3.amazonaws.com/uifaces/faces/twitter/fuck_you_two/128.jpg",
                                "Rick83@gmail.com"
                            ),
                            User(
                                "Terrance Halvorson",
                                "http://s3.amazonaws.com/uifaces/faces/twitter/mufaddal_mw/128.jpg",
                                "Kenton_Wisozk@hotmail.com"
                            ),
                            User(
                                "Morgan McGlynn",
                                "http://s3.amazonaws.com/uifaces/faces/twitter/allfordesign/128.jpg",
                                "Adah_Streich14@gmail.com"
                            ),
                            User(
                                "Ms. Ramiro DuBuque",
                                "http://s3.amazonaws.com/uifaces/faces/twitter/shaneIxD/128.jpg",
                                "Forrest_Toy@yahoo.com"
                            ),
                            User(
                                "Kolby Orn",
                                "http://s3.amazonaws.com/uifaces/faces/twitter/johnsmithagency/128.jpg",
                                "Kay.Kuhn22@yahoo.com"
                            ),
                            User(
                                "Elijah Schoen MD",
                                "http://s3.amazonaws.com/uifaces/faces/twitter/alxndrustinov/128.jpg",
                                "Mya_Leuschke@yahoo.com"
                            ),
                            User(
                                "Colton Kohler",
                                "http://s3.amazonaws.com/uifaces/faces/twitter/gearpixels/128.jpg",
                                "Stephen49@hotmail.com"
                            )
                        )
                    )

            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUsersRealState(): Observable<PartialMainViewState> {
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
