package com.yandex.together.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yandex.together.databinding.FragmentLoginBinding
import com.yandex.together.ui.MainActivity
import com.yandex.together.ui.setOnClickWithTouchImpact
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null
    private val viewModel: LoginViewModel by viewModel()

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
        observeLoginResult()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupListeners() {
        binding?.btnLogin?.setOnClickWithTouchImpact {
            viewModel.login(binding?.etLogin?.text.toString(), binding?.etPassword?.text.toString())
        }
    }

    private fun observeLoginResult() {
        viewModel.eventsLiveData.observe(viewLifecycleOwner, {
            it.doOnResult(
                onSuccess = {
                    (requireActivity() as MainActivity).openMainScreen()
                },
                onError = {
                    Toast.makeText(
                        requireContext(),
                        "Что-то пошло так. Попробуйте снова",
                        Toast.LENGTH_LONG
                    ).show()
                }
            )
        })
    }
}