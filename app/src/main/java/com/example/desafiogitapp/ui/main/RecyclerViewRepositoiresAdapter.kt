package com.example.desafiogitapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiogitapp.R
import com.example.desafiogitapp.data.model.Items

class RecyclerViewRepositoiresAdapter(
    private val repositories: ArrayList<Items>,
    private val listener: OnRepositorieClickListener
) : RecyclerView.Adapter<RecyclerViewRepositoiresAdapter.MyHolder>() {

    class MyHolder(val view: View): RecyclerView.ViewHolder(view) {
        val nameRepositoireTextView = view.findViewById<TextView>(R.id.nameRepositorieTextView)
        val authorTextView = view.findViewById<TextView>(R.id.authorTextView)

        fun bind(repositorie: Items) {
            nameRepositoireTextView.text = repositorie.name
            authorTextView.text = repositorie.forks.toString()
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