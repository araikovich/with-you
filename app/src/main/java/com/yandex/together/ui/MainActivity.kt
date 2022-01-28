package com.yandex.together.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yandex.together.R
import com.yandex.together.databinding.ActivityMainBinding
import com.yandex.together.ui.login.LoginFragment
import com.yandex.together.ui.main_screen.MainScreenFragment
import com.yandex.together.ui.new_event.NewEventFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val viewMode: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        checkIsLoggedIn()
    }

    fun startNewNoteFragment() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right_anim, R.anim.exit_left_to_right_anim
            )
            .replace(binding!!.fragmentContainerView.id, NewEventFragment())
            .addToBackStack("NewEventFragment")
            .commitAllowingStateLoss()
    }

    private fun checkIsLoggedIn() {
        if (!viewMode.isLoggedIn()) {
            supportFragmentManager.beginTransaction()
                .replace(binding!!.fragmentContainerView.id, LoginFragment())
                .commitAllowingStateLoss()
        }
    }

    fun openMainScreen() {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_right_to_left, R.anim.exit_right_to_left,
                R.anim.enter_left_to_right_anim, R.anim.exit_left_to_right_anim
            )
            .replace(binding!!.fragmentContainerView.id, MainScreenFragment())
            .commitAllowingStateLoss()
    }
}