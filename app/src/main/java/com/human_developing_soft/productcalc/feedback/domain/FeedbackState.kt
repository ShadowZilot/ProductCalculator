package com.human_developing_soft.productcalc.feedback.domain

import androidx.annotation.StringRes

sealed class FeedbackState {
    object Default : FeedbackState()

    object Loading : FeedbackState()

    object Success : FeedbackState()
}

class FeedbackError(
    @StringRes val errorDescription: Int
)