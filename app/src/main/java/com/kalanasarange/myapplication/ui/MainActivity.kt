package com.kalanasarange.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kalanasarange.myapplication.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * -- Requirement given by Vinayaka --
 * Create a Retrofit client to consume a REST API, fetch a list of users,
 * and display it in a RecyclerView. Use coroutines for async processing.
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
