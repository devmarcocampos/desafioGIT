package com.example.desafiogitapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.desafiogitapp.R
import com.example.desafiogitapp.data.model.Items
import com.squareup.picasso.Picasso

class DetailsFragment(
    private val repositorie: Items
) : Fragment() {

    companion object {
        fun newInstance(repositorie: Items) = DetailsFragment(repositorie)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        Toast.makeText(activity, "Detalhes Fragment -" + repositorie.name, Toast.LENGTH_LONG).show()

        showDetails()
    }

    private fun showDetails() {
        val ownerImageView = view?.findViewById<ImageView>(R.id.ownerImageView)
        val ownerNameTextView = view?.findViewById<TextView>(R.id.ownerNameTextView)
        val repositorieNameTextView = view?.findViewById<TextView>(R.id.repositorieNameTextView)
        val repositorieStarsTextView = view?.findViewById<TextView>(R.id.repositorieStarsTextView)
        val repositorieForksTextView = view?.findViewById<TextView>(R.id.repositorieForksTextView)

        ownerNameTextView?.text = repositorie.owner.login
        repositorieNameTextView?.text = repositorie.name
        repositorieStarsTextView?.text = repositorie.stargazersCount.toString()
        repositorieForksTextView?.text = repositorie.forks.toString()

        val picasso = activity?.let {
            Picasso.Builder(it).listener { _, _, exception ->
            exception?.printStackTrace()
            println("Picasso loading failed : ${exception?.message}")
            ownerImageView?.setImageResource(R.drawable.ic_launcher_background)
        }.build()
        }

        picasso?.let { pic ->
            pic.load(repositorie.owner.avatar_url)
                    .fit().centerCrop()
                    .into(ownerImageView)
        }
    }
}