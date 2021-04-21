package com.example.desafiogitapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.desafiogitapp.R
import com.example.desafiogitapp.data.model.Repository
import com.squareup.picasso.Picasso

class DetailsFragment(
    private val repository: Repository
) : Fragment() {

    companion object {
        fun newInstance(repository: Repository) = DetailsFragment(repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        showDetails()
    }

    private fun showDetails() {
        val ownerImageView = view?.findViewById<ImageView>(R.id.ownerImageView)
        val ownerNameTextView = view?.findViewById<TextView>(R.id.ownerNameTextView)
        val repositoryNameTextView = view?.findViewById<TextView>(R.id.repositoryNameTextView)
        val repositoryStarsTextView = view?.findViewById<TextView>(R.id.repositoryStarsTextView)
        val repositoryForksTextView = view?.findViewById<TextView>(R.id.repositoryForksTextView)

        ownerNameTextView?.text = repository.owner.login
        repositoryNameTextView?.text = repository.name
        repositoryStarsTextView?.text = repository.stargazersCount.toString()
        repositoryForksTextView?.text = repository.forks.toString()

        val picasso = activity?.let {
            Picasso.Builder(it).listener { _, _, exception ->
            exception?.printStackTrace()
            println("Picasso loading failed : ${exception?.message}")
            ownerImageView?.setImageResource(R.drawable.ic_launcher_background)
        }.build()
        }

        picasso?.let { pic ->
            pic.load(repository.owner.avatar_url)
                    .fit().centerCrop()
                    .into(ownerImageView)
        }
    }
}