package com.example.coroutineswitherrorhandling.learn.retrofit.ExceptionHandler

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutineswitherrorhandling.data.api.ApiHelperImpl
import com.example.coroutineswitherrorhandling.data.api.RetrofitBuilder
import com.example.coroutineswitherrorhandling.data.model.ApiUser
import com.example.coroutineswitherrorhandling.learn.base.ApiUserAdapter
import com.example.coroutineswitherrorhandling.utils.Status
import com.example.coroutineswitherrorhandling.utils.ViewModelFactory
import com.example.coroutineswitherrorhandling.R

class ExceptionHandlerActivity : AppCompatActivity() {

    private lateinit var viewModel: ExceptionHandlerViewModel
    private lateinit var adapter: ApiUserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var list: MutableList<ApiUser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        list= arrayListOf()
        findIDs()
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun findIDs() {
        recyclerView=findViewById(R.id.recyclerView)
        progressBar=findViewById(R.id.progressBar)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            ApiUserAdapter(
                list
            )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun renderList(users: List<ApiUser>) {
        list.addAll(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        )[ExceptionHandlerViewModel::class.java]
    }
}
