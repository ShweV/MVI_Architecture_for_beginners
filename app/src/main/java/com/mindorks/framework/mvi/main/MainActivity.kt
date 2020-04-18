package com.mindorks.framework.mvi.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.mindorks.framework.mvi.R
import com.mindorks.framework.mvi.data.model.User
import com.mindorks.framework.mvi.main.adapter.UserListAdapter
import com.mindorks.framework.mvi.main.statemodel.UserViewState
import com.mindorks.framework.mvi.main.view.UserView
import com.mindorks.framework.mvi.main.viewmodel.UserViewModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(),UserView {

    private val userListAdapter = UserListAdapter()
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        userViewModel = ViewModelProviders.of(this)[UserViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        userViewModel.bind(this)
    }

    override fun onStop() {
        userViewModel.unbind()
        super.onStop()
    }

    private fun initRecyclerView() {
        user_recycler.layoutManager = LinearLayoutManager(this)
        user_recycler.adapter = userListAdapter
    }

    private fun showProgressBar(show: Boolean) {
        if (show) {
            user_recycler.visibility = View.GONE
            error_text.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        } else {
            user_recycler.visibility = View.VISIBLE
            error_text.visibility = View.GONE
            progressBar.visibility = View.GONE
        }
    }

    private fun showError(show: Boolean) {
        if (show) {
            user_recycler.visibility = View.GONE
            error_text.visibility = View.VISIBLE
        } else {
            error_text.visibility = View.GONE
        }
    }

    private fun showUsersList(userList: List<User>) {
        userListAdapter.setUserList(userList)
    }

    override fun onButtonClick(): Observable<Boolean> {
        return display_user_button.clicks().map { true }
    }


    override fun fetch(userViewState: UserViewState) {
        with(userViewState) {
            showProgressBar(progress)
            showError(error)
            showUsersList(userList)
        }
    }
}
