package com.example.desafiogitapp.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiogitapp.R
import com.example.desafiogitapp.data.model.Items

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)

        val repositorieSelected = intent.getSerializableExtra("repositorieSelected") as? Items

        if (savedInstanceState == null) {
            repositorieSelected?.let { DetailsFragment.newInstance(it) }?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, it)
                    .commitNow()
            }
        }

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, DetailsFragment.newInstance(repositorieSelected))
//                    .commitNow()
//        }
    }
}