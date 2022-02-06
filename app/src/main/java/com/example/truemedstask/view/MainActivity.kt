package com.example.truemedstask.view

import android.os.Bundle
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.example.truemedstask.R
import com.example.truemedstask.core.BindingActivity
import com.example.truemedstask.databinding.ActivityMainBinding
import com.example.truemedstask.viewmodel.MainActivityViewModel

class MainActivity : BindingActivity<ActivityMainBinding>() {

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initObservers()
    }

    fun initObservers() {

    }

    override fun onResume() {
        super.onResume()
        viewModel.startCountDownTimer()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }
}