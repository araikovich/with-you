package com.yandex.together.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yandex.together.databinding.FragmentLoginBinding
import com.yandex.together.ui.MainActivity
import com.yandex.together.ui.setOnClickWithTouchImpact

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupListeners() {
        binding?.btnLogin?.setOnClickWithTouchImpact {
            (requireActivity() as MainActivity).openMainScreen()
        }
    }
}