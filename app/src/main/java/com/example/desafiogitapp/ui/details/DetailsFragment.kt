package com.example.desafiogitapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.desafiogitapp.R
import com.example.desafiogitapp.data.model.Items

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

        Toast.makeText(activity, "Detalhes Fragment -" + repositorie.name, Toast.LENGTH_LONG).show()
    }
}