package com.mindorks.framework.mvi.main.statemodel

import com.mindorks.framework.mvi.data.model.User

data class UserViewState(val progress: Boolean = false,
                             val error: Boolean = false,
                             val userList: List<User> = listOf())
