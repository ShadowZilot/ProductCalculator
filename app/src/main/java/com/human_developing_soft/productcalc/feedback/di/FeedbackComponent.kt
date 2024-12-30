package com.human_developing_soft.productcalc.feedback.di

import com.human_developing_soft.productcalc.di.ApplicationComponent
import com.human_developing_soft.productcalc.di.PerFragment
import com.human_developing_soft.productcalc.di.ViewModelFactoryModule
import com.human_developing_soft.productcalc.feedback.ui.FeedbackFragment
import dagger.Component

@PerFragment
@Component(
    modules = [FeedbackModule::class, ViewModelFactoryModule::class],
    dependencies = [ApplicationComponent::class]
)
interface FeedbackComponent {

    fun inject(fragment: FeedbackFragment)
}