package com.human_developing_soft.productcalc.feedback.domain

import androidx.annotation.StringRes

sealed class FeedbackState {

    object Loading : FeedbackState()

    object Success : FeedbackState()

    class Error(
        @StringRes val errorDescription: Int
    ) : FeedbackState()
}