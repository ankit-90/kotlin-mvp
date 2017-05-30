package com.kotlindemo.flow.repository.repository_list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import client.yalantis.com.githubclient.model.Repository
import com.kotlindemo.R
import kotlinx.android.synthetic.main.item_repository.view.*

/**
 * Created by ankit on 30/5/17.
 */
class RepositoryListAdapter(private val repositories: MutableList<Repository>)
    : RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder:ViewHolder ?, position: Int) {

        holder?.bindData(repositories[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder{
        return LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_repository, parent, false).let {
            ViewHolder(it)
        }
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView) {

        fun bindData(repos:Repository){
            with(repos){
                itemView.text_view_title.text = name
                itemView.text_view_description.text = description

            }
        }

    }

    fun updateAdapter(repos:MutableList<Repository>){
        repositories.addAll(repos)
    }



}




