package com.human_developing_soft.productcalc.feedback.di

import androidx.lifecycle.ViewModel
import com.human_developing_soft.productcalc.di.ViewModelKey
import com.human_developing_soft.productcalc.feedback.data.BotKeysValueProvider
import com.human_developing_soft.productcalc.feedback.data.BotKeysValueProviderImpl
import com.human_developing_soft.productcalc.feedback.data.FeedbackFileStorage
import com.human_developing_soft.productcalc.feedback.data.FeedbackFileStorageImpl
import com.human_developing_soft.productcalc.feedback.data.FeedbackRepository
import com.human_developing_soft.productcalc.feedback.data.FeedbackRepositoryImpl
import com.human_developing_soft.productcalc.feedback.domain.FeedbackViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FeedbackModule {

    @Binds
    @IntoMap
    @ViewModelKey(FeedbackViewModel::class)
    abstract fun bindFeedbackViewModel(viewModel: FeedbackViewModel): ViewModel

    @Binds
    fun bindFeedbackRepository(impl: FeedbackRepositoryImpl): FeedbackRepository

    @Binds
    fun bindBotKeysValueProvider(impl: BotKeysValueProviderImpl): BotKeysValueProvider

    @Binds
    fun bindsFeedbackFileStorage(impl: FeedbackFileStorageImpl): FeedbackFileStorage
}