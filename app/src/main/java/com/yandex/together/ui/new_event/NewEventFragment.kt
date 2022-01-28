package com.yandex.together.ui.new_event

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yandex.together.data.api.EventAddress
import com.yandex.together.data.api.NewEventBody
import com.yandex.together.databinding.FragmentNewEventBinding
import com.yandex.together.ui.setOnClickWithTouchImpact
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class NewEventFragment : Fragment() {

    private var binding: FragmentNewEventBinding? = null
    private val viewModel: NewEventViewModel by viewModel()

    private var timeStartHours: Int = 0
    private var timeStartMinutes: Int = 0
    private var timeStartYear: Int = 0
    private var timeStartMonth: Int = 0
    private var timeStartDay: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewEventBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        observeResults()
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun setupListeners() {
        binding?.btnTime?.setOnClickWithTouchImpact {
            showPickTimeDialog()
        }
        binding?.btnDate?.setOnClickWithTouchImpact {
            showDatePickDialog()
        }
        binding?.btnTakePart?.setOnClickWithTouchImpact {
            viewModel.newEvent(
                NewEventBody(
                    "araikovich",
                    binding?.etTitle?.text.toString(),
                    "Спорт",
                    binding?.etDescription?.text.toString(),
                    binding?.etTelegram?.text.toString(),
                    binding?.etPerson?.text.toString().toInt(),
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(
                        Date(
                            timeStartYear,
                            timeStartMonth,
                            timeStartDay,
                            timeStartHours,
                            timeStartMinutes,
                            0
                        )
                    ),//"2022-03-27T02:12:15Z",
                    EventAddress(
                        0, 0, binding?.etPlace?.text.toString()
                    )
                )
            )
        }
    }

    private fun observeResults() {
        viewModel.eventsLiveData.observe(viewLifecycleOwner, {
            parentFragmentManager.popBackStack()
        })
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
            { _, int1, int2, int3 ->
                binding?.btnDate?.text =
                    "${int3}:${int2}:${int1}"
                timeStartYear = int1
                timeStartMinutes = int2
                timeStartDay = int3
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