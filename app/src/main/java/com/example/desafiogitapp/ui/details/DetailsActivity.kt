package com.example.desafiogitapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiogitapp.R
import com.example.desafiogitapp.data.model.Repository

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        val repositorySelected = intent.getSerializableExtra("repositorySelected") as? Repository

        if (savedInstanceState == null) {
            repositorySelected?.let { DetailsFragment.newInstance(it) }?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, it)
                    .commitNow()
            }
        }
    }
}