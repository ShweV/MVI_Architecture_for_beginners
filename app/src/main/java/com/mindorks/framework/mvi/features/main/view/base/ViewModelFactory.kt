package com.mindorks.framework.mvi.features.main.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindorks.framework.mvi.data.api.ApiHelper

class ViewModelFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
//            return UserViewModel(UserRepository(apiHelper)) as T
//        }
        throw IllegalArgumentException("Unknown class name")
    }

}