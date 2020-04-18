package com.mindorks.framework.mvi.main.statemodel

import com.mindorks.framework.mvi.data.model.User
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import io.reactivex.android.schedulers.AndroidSchedulers

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
                            User("Dr. Edwin Orn","https://s3.amazonaws.com/uifaces/faces/twitter/sunlandictwin/128.jpg","Connor.Hartmann71@gmail.com"),
                            User("Alford Hoeger","https://s3.amazonaws.com/uifaces/faces/twitter/fuck_you_two/128.jpg","Rick83@gmail.com"),
                            User("Terrance Halvorson","https://s3.amazonaws.com/uifaces/faces/twitter/mufaddal_mw/128.jpg","Kenton_Wisozk@hotmail.com"),
                            User("Morgan McGlynn","https://s3.amazonaws.com/uifaces/faces/twitter/allfordesign/128.jpg","Adah_Streich14@gmail.com"),
                            User("Ms. Ramiro DuBuque","https://s3.amazonaws.com/uifaces/faces/twitter/shaneIxD/128.jpg","Forrest_Toy@yahoo.com"),
                            User("Kolby Orn","https://s3.amazonaws.com/uifaces/faces/twitter/johnsmithagency/128.jpg","Kay.Kuhn22@yahoo.com"),
                            User("Elijah Schoen MD","https://s3.amazonaws.com/uifaces/faces/twitter/alxndrustinov/128.jpg","Mya_Leuschke@yahoo.com"),
                            User("Colton Kohler","https://s3.amazonaws.com/uifaces/faces/twitter/gearpixels/128.jpg","Stephen49@hotmail.com")))

            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}
