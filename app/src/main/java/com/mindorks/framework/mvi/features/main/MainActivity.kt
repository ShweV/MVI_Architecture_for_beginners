package com.mindorks.framework.mvi.features.main.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jakewharton.rxbinding3.view.clicks
import com.mindorks.framework.mvi.R
import com.mindorks.framework.mvi.data.model.User
import com.mindorks.framework.mvi.features.main.main.adapter.UserListAdapter
import com.mindorks.framework.mvi.features.main.main.statemodel.UserViewState
import com.mindorks.framework.mvi.features.main.main.view.UserView
import com.mindorks.framework.mvi.features.main.main.viewmodel.UserViewModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),UserView {

    private val userListAdapter = UserListAdapter()
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
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

    private fun showProgress(show: Boolean) {
        if (show) {
            showProgressBar()
        } else {
            hideProgressBar()
        }
    }

    private fun showError(show: Boolean) {
        if (show) {
            showErrorMessage()
        } else {
            //Hide error message
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
            showProgress(progress)
            showError(error)
            showUsersList(userList)
        }
    }

    private fun showProgressBar() {
        user_recycler.visibility = View.GONE
        error_text.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        user_recycler.visibility = View.VISIBLE
        error_text.visibility = View.GONE
        progressBar.visibility = View.GONE
    }

    private fun showErrorMessage(){
        user_recycler.visibility = View.GONE
        error_text.visibility = View.VISIBLE
    }
}
