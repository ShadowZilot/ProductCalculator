package com.human_developing_soft.productcalc.feedback.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.ActivityNotFoundException
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.databinding.FeedbackFragmentBinding
import com.human_developing_soft.productcalc.feedback.di.DaggerFeedbackComponent
import com.human_developing_soft.productcalc.feedback.di.FeedbackComponent
import com.human_developing_soft.productcalc.feedback.domain.FeedbackState
import com.human_developing_soft.productcalc.feedback.domain.FeedbackViewModel
import com.human_developing_soft.productcalc.main.ui.BaseFragment
import com.human_developing_soft.productcalc.navigation.Navigation
import kotlinx.coroutines.launch
import javax.inject.Inject


class FeedbackFragment : BaseFragment<FeedbackFragmentBinding, FeedbackComponent>(
    bindingInflater = { inflater, container ->
        FeedbackFragmentBinding.inflate(inflater, container, false)
    }
), ActivityResultCallback<Uri?> {
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    private lateinit var viewModel: FeedbackViewModel
    private val visualMediaPicker by lazy {
        registerForActivityResult(ActivityResultContracts.PickVisualMedia(), this)
    }

    override fun component(): FeedbackComponent = DaggerFeedbackComponent.builder()
        .applicationComponent(myApplication.appComponent)
        .build()

    override fun onComponentReady(component: FeedbackComponent) {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Register activity result callback
        visualMediaPicker
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytic()?.logEvent("Feedback_action", null)
        viewModel = ViewModelProvider(this, viewModelProvider)[FeedbackViewModel::class]
        binding.sendFeedBack.setOnClickListener {
            viewModel.sendFeedback(
                binding.problemDescription.text.toString(),
                binding.contactField.text.toString(),
            )
        }
        binding.attachFile.setOnClickListener { openFilePicker() }
        binding.removeFile.setOnClickListener { viewModel.removeFile() }
        binding.backButton.setOnClickListener { Navigation.Navigation.instance().takeBack() }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.attachedFile.collect { attachedFile ->
                    if (attachedFile == null) {
                        binding.attachedFileIcon.visibility = View.GONE
                        binding.removeFile.visibility = View.GONE
                        binding.attachFile.visibility = View.VISIBLE
                        binding.fileFieldText.setText(R.string.feedback_file_field)
                    } else {
                        binding.attachedFileIcon.visibility = View.VISIBLE
                        binding.removeFile.visibility = View.VISIBLE
                        binding.attachFile.visibility = View.GONE
                        binding.fileFieldText.text = attachedFile.fileName
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.errorFlow.collect { errorState ->
                    binding.sendFeedBack.setText(R.string.send_feedback_label)
                    binding.feedbackLoader.visibility = View.GONE
                    AlertDialog.Builder(requireContext())
                        .setTitle(R.string.error_title)
                        .setMessage(errorState.errorDescription)
                        .setPositiveButton(
                            android.R.string.ok
                        ) { dialog, _ -> dialog.dismiss() }
                        .show()
                }
            }
        }
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.feedbackState.collect { state ->
                    when (state) {
                        FeedbackState.Default -> {
                            binding.sendFeedBack.setText(R.string.send_feedback_label)
                            binding.feedbackLoader.visibility = View.GONE
                        }

                        FeedbackState.Loading -> {
                            binding.sendFeedBack.text = null
                            binding.feedbackLoader.visibility = View.VISIBLE
                        }

                        FeedbackState.Success -> {
                            binding.sendFeedBack.setText(R.string.send_feedback_label)
                            binding.feedbackLoader.visibility = View.GONE
                            successScenario()
                        }
                    }
                }
            }
        }
    }

    private fun successScenario() {
        binding.feedbackSuccessContainer.visibility = View.VISIBLE
        val backgroundAnimation = AnimatorSet().apply {
            playTogether(
                ObjectAnimator.ofFloat(
                    binding.successBackground,
                    "scaleX", 1f, 2_000f
                ),
                ObjectAnimator.ofFloat(
                    binding.successBackground,
                    "scaleY", 1f, 2_000f
                ),
            )
            duration = 500
        }
        backgroundAnimation.start()
    }

    override fun onActivityResult(result: Uri?) {
        result?.let(viewModel::saveFeedbackFile)
    }

    override fun onDestroy() {
        super.onDestroy()
        visualMediaPicker.unregister()
    }

    private fun openFilePicker() {
        try {
            visualMediaPicker.launch(PickVisualMediaRequest())
        } catch (ex: ActivityNotFoundException) {
            // TODO make message about error
        }
    }
}