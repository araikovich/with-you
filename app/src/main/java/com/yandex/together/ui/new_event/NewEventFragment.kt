package com.yandex.together.ui.new_event

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yandex.together.databinding.FragmentNewNoteBinding

class NewEventFragment : Fragment() {

    private var binding: FragmentNewNoteBinding? = null

    private var timeStartHours: Int = 0
    private var timeStartMinutes: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewNoteBinding.inflate(inflater, container, false)
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
        binding?.btnTime?.setOnClickListener {
            showPickTimeDialog()
        }
        binding?.btnDate?.setOnClickListener {
            showDatePickDialog()
        }
    }

    private fun showPickTimeDialog() {
        val picker = TimePickerDialog(
            requireActivity(),
            { tp, hour, minute ->
                binding?.btnTime?.text =
                    "${checkDigit(hour)}:${checkDigit(minute)}"
                timeStartHours = hour
                timeStartMinutes = minute
            },
            timeStartHours,
            timeStartMinutes,
            true
        )
        picker.show()
    }

    private fun showDatePickDialog() {
        val picker = DatePickerDialog(
            requireActivity(),
            {_, int1, int2, int3  ->
                binding?.btnDate?.text =
                    "${int3}:${int2}:${int1}"
            },
            2022,
            2,
            27
        )
        picker.show()
    }

    private fun checkDigit(number: Int): String {
        return if (number <= 9) "0$number" else number.toString()
    }
}