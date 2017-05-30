package com.kotlindemo.flow.repository

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import client.yalantis.com.githubclient.model.Repository
import com.kotlindemo.R
import com.kotlindemo.flow.repository.repository_list.RepositoryContract
import com.kotlindemo.flow.repository.repository_list.RepositoryListAdapter
import com.kotlindemo.flow.repository.repository_list.RepositoryPresenter
import com.kotlindemo.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : BaseActivity<RepositoryContract.View, RepositoryPresenter>(),
        RepositoryContract.View {

    val TAG = RepositoryActivity::class.java.name

    override var mPresenter: RepositoryPresenter = RepositoryPresenter()
    private var mAdapter: RepositoryListAdapter? = null

    override fun showOrganization(repos: MutableList<Repository>) {
        Log.d(TAG, repos.get(0).name)
        mAdapter?.updateAdapter(repos)
        mAdapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        setUpRecyclerView()
        mPresenter.getRepos()

    }

    private fun setUpRecyclerView() {
        mAdapter = RepositoryListAdapter(ArrayList())
        recyclerViewRepositories.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewRepositories.adapter = mAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.dettachView()
    }

}
