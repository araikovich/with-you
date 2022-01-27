package com.yandex.together.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yandex.together.databinding.ActivityMainBinding
import com.yandex.together.ui.new_event.NewEventFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }

    fun startNewNoteFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding!!.fragmentContainerView.id, NewEventFragment())
            .addToBackStack("NewEventFragment")
            .commitAllowingStateLoss()
    }
}