package com.yandex.together.ui.event_detail

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.view.*
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yandex.together.R
import com.yandex.together.databinding.FragmnetDialogEventDetailBinding
import com.yandex.together.ui.orZero
import com.yandex.together.ui.setOnClickWithTouchImpact
import com.yandex.together.ui.toolBarHeight
import kotlinx.android.parcel.Parcelize

class EventDetailBottomSheetDialogFragment : BottomSheetDialogFragment() {

    protected var bottomSheet: View? = null
    protected var bottomSheetBehavior: BottomSheetBehavior<View?>? = null
    private var binding: FragmnetDialogEventDetailBinding? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        requireActivity().toolBarHeight()
        val bottomSheetDialog =
            BottomSheetDialog(requireContext(), R.style.MyBottomSheetDialogTheme)
        bottomSheetDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        bottomSheetDialog.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        bottomSheetDialog.setOnShowListener {
            val dialog = it as BottomSheetDialog
            bottomSheet = dialog.findViewById<View>(R.id.design_bottom_sheet)?.apply {
                val params = this.layoutParams as ViewGroup.MarginLayoutParams
                params.setMargins(0, activity?.toolBarHeight().orZero(), 0, 0)
                params.height = ViewGroup.MarginLayoutParams.MATCH_PARENT
                requestLayout()
            }
            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!).apply {
                state = BottomSheetBehavior.STATE_EXPANDED
                skipCollapsed = true
                isHideable = true
                addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(p0: View, p1: Float) {}

                    override fun onStateChanged(p0: View, state: Int) {
                        if (state == BottomSheetBehavior.STATE_HIDDEN) {
                            dismiss()
                        }
                    }
                })
            }
        }
        return bottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmnetDialogEventDetailBinding.inflate(inflater, container, false)
        val vo = arguments?.getParcelable<EventDetailVO>("vo")
        vo?.let {
            binding?.tvTitle?.text = it.title
            binding?.tvDescription?.text = it.description
            binding?.tvPersons?.text = "${it.personsCount}/${it.total}"
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding?.btnTakePart?.setOnClickListener {
            binding?.btnTakePart?.setBackgroundResource(R.color.transparent)
            binding?.btnTakePart?.text = "Вы участвуете"
            binding?.btnTakePart?.isEnabled = false
            binding?.btnTakePart?.isClickable = false
        }
        binding?.chat?.setOnClickWithTouchImpact {
            val intent1 = Intent(Intent.ACTION_VIEW, Uri.parse(arguments?.getParcelable<EventDetailVO>("vo")?.chatLink.orEmpty()))
            startActivity(intent1)
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        manager.beginTransaction().add(this, tag).commitAllowingStateLoss()
    }
}

@Parcelize
data class EventDetailVO(
    val title: String,
    val description: String,
    val personsCount: Int,
    val total: Int,
    val chatLink: String,
) : Parcelable