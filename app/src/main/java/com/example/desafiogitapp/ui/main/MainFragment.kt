package com.example.desafiogitapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiogitapp.R
import com.example.desafiogitapp.data.model.Items
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment(), OnRepositorieClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by viewModel()

    private var repositoriesList =  ArrayList<Items>()

    private var contPage = 0

    private var firstRequest = true

    private var myRecyclerView: RecyclerView? = null
    lateinit var myRepositoriesAdapter: RecyclerViewRepositoiresAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel.states.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is MainViewState.ShowRepositories -> showRepositories(state.repositories)
                is MainViewState.ShowError -> showError(state.error)
            }
        })

        configureRecyclerView()
        getRepositories()

//        mainViewModel.getRepositories(1)

    }

    private fun configureRecyclerView() {
        myRecyclerView = view?.findViewById<RecyclerView>(R.id.repositoriesRecyclerView)

        myRecyclerView?.let { recyclerView ->
            with(recyclerView) {
                layoutManager = LinearLayoutManager(activity)
                setHasFixedSize(false)
            }
        }

        myRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    getRepositories()
                }
            }
        })
    }

    private fun showRepositories(repositories: ArrayList<Items>) {
//        Toast.makeText(activity, repositories[1].name, Toast.LENGTH_LONG).show()

        repositoriesList.addAll(repositories)

        myRepositoriesAdapter = RecyclerViewRepositoiresAdapter(repositoriesList, this)

        myRecyclerView?.adapter = myRepositoriesAdapter

        if (!firstRequest) {
            myRecyclerView?.scrollToPosition(repositoriesList.size - repositories.size)
        }

        firstRequest = false


//        val repositoriesRecyclerView = view?.findViewById<RecyclerView>(R.id.repositoriesRecyclerView)



//        repositoriesRecyclerView?.let { recyclerView ->
//            with(recyclerView) {
//                layoutManager = LinearLayoutManager(activity)
//                adapter = repositoriesAdapter
//                setHasFixedSize(false)
//            }
//        }
//
//        repositoriesRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    getRepositories()
//                }
//            }
//        })
    }

    private fun getRepositories() {
        contPage += 1
        mainViewModel.getRepositories(contPage)
    }

    private fun showError(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show()
    }

    override fun onRepositorieClicked(repositorie: Items) {
        Toast.makeText(activity, repositorie.name, Toast.LENGTH_LONG).show()
    }

}