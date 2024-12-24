package com.human_developing_soft.productcalc

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.BuildConfig
import com.human_developing_soft.productcalc.main.data.SharedPreferencesShell
import com.human_developing_soft.productcalc.main.domain.AnalyticsApp
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.review.domain.sBeginUseApp
import java.util.Date

const val sReviewGlobal = "review"
const val sFirstRun = "firstRun"

class MainApp : Application(), AnalyticsApp {
    private val mMainNavigation = Navigation.Navigation
    private var mAnalytic : FirebaseAnalytics? = null

    override fun onCreate() {
        super.onCreate()
        val shell = SharedPreferencesShell.Base(applicationContext, sReviewGlobal)
        if (shell.valueBoolean(sFirstRun, true)) {
            shell.edit {
                it.putLong(sBeginUseApp, Date().time)
                it.putBoolean(sFirstRun, false)
                it.apply()
            }
        }
        if (!BuildConfig.DEBUG) {
            mAnalytic = FirebaseAnalytics.getInstance(this)
        }
    }

    override fun instance(): FirebaseAnalytics? {
        return mAnalytic
    }
}