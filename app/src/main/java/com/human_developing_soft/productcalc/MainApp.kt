package com.human_developing_soft.productcalc

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.human_developing_soft.productcalc.navigation.Navigation

class MainApp : Application(), AnalyticsApp {
    private val mMainNavigation = Navigation.Navigation
    private var mAnalytic : FirebaseAnalytics? = null

    override fun onCreate() {
        super.onCreate()
        if (!BuildConfig.DEBUG) {
            mAnalytic = FirebaseAnalytics.getInstance(this)
        }
    }

    override fun instance(): FirebaseAnalytics? {
        return mAnalytic
    }
}