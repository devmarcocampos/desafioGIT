package com.example.desafiogitapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiogitapp.R
import com.example.desafiogitapp.data.model.Items
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class RecyclerViewRepositoiresAdapter(
    private val repositories: ArrayList<Items>,
    private val listener: OnRepositorieClickListener
) : RecyclerView.Adapter<RecyclerViewRepositoiresAdapter.MyHolder>() {

    class MyHolder(val view: View): RecyclerView.ViewHolder(view) {
        val nameRepositoireTextView = view.findViewById<TextView>(R.id.nameRepositorieTextView)
        val starsCountTextView = view.findViewById<TextView>(R.id.starsCountTextView)
        val forkCountTextView = view.findViewById<TextView>(R.id.forkCountTextView)
        val ownerImageView = view.findViewById<ShapeableImageView>(R.id.ownerImageView)
        val ownerNameTextView = view.findViewById<TextView>(R.id.ownerNameTextView)

        fun bind(repositorie: Items) {
            nameRepositoireTextView.text = repositorie.name
            starsCountTextView.text = repositorie.stargazersCount.toString()
            forkCountTextView.text = repositorie.forks.toString()
            ownerNameTextView.text = repositorie.owner.login

            val picasso = Picasso.Builder(view.context).listener { _, _, exception ->
                exception?.printStackTrace()
                println("Picasso loading failed : ${exception?.message}")
                ownerImageView.setImageResource(R.drawable.ic_launcher_background)
            }.build()

            picasso.load(repositorie.owner.avatar_url)
                .noFade()
                .fit().centerCrop()
                .into(ownerImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder =
        MyHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repositorie, parent, false))

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(repositories[position])
        holder.itemView.setOnClickListener {
            listener.onRepositorieClicked(repositories[position])
        }
    }
}