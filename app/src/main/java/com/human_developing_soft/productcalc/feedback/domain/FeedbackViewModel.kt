package com.human_developing_soft.productcalc.feedback.domain

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.human_developing_soft.productcalc.R
import com.human_developing_soft.productcalc.feedback.data.AttachedFileData
import com.human_developing_soft.productcalc.feedback.data.FeedbackData
import com.human_developing_soft.productcalc.feedback.data.FeedbackFileStorage
import com.human_developing_soft.productcalc.feedback.data.FeedbackRepository
import com.human_developing_soft.productcalc.feedback.data.FileTooBigException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class FeedbackViewModel @Inject constructor(
    private val feedbackRepository: FeedbackRepository,
    private val feedbackFileStorage: FeedbackFileStorage,
) : ViewModel() {
    val feedbackState = MutableSharedFlow<FeedbackState>()
    val attachedFile = MutableStateFlow<AttachedFileData?>(null)
    private val feedbackContext = CoroutineExceptionHandler { _, exception ->
        val state = when (exception) {
            is UnknownHostException, is IOException -> FeedbackState.Error(
                R.string.error_no_internet
            )

            is FileTooBigException -> FeedbackState.Error(R.string.error_file_too_big_message)
            else -> FeedbackState.Error(R.string.error_message_general)
        }
        FirebaseCrashlytics.getInstance().recordException(exception)
        viewModelScope.launch { feedbackState.emit(state) }
    }

    fun sendFeedback(
        feedbackBody: String?,
        userContact: String?
    ) {
        FirebaseCrashlytics.getInstance().log("Feedback try send")
        viewModelScope.launch(feedbackContext) {
            feedbackState.emit(FeedbackState.Loading)
            if (feedbackBody?.isEmpty() == true) {
                feedbackState.emit(FeedbackState.Error(R.string.error_empty_feedback_message))
                return@launch
            }
            feedbackRepository.sendFeedback(
                FeedbackData(
                    feedbackBody,
                    userContact,
                    attachedFile.value,
                )
            )
            feedbackState.emit(FeedbackState.Success)
        }
    }

    fun saveFeedbackFile(uri: Uri) {
        viewModelScope.launch(feedbackContext) {
            attachedFile.emit(feedbackFileStorage.saveFileByUri(uri))
        }
    }

    fun removeFile() {
        viewModelScope.launch(feedbackContext) { attachedFile.emit(null) }
    }
}