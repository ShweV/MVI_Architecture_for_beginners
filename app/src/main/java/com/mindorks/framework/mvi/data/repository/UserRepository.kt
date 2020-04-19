package com.mindorks.framework.mvi.data.repository

import com.mindorks.framework.mvi.data.api.ApiHelper
import com.mindorks.framework.mvi.data.model.User
import io.reactivex.Single

class UserRepository (private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<User>>? {
        return apiHelper.getUsers()
    }
}