package com.human_developing_soft.productcalc

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.human_developing_soft.productcalc.di.ApplicationComponent
import com.human_developing_soft.productcalc.di.DaggerApplicationComponent
import com.human_developing_soft.productcalc.main.data.SharedPreferencesShell
import com.human_developing_soft.productcalc.main.domain.AnalyticsApp
import com.human_developing_soft.productcalc.navigation.Navigation
import com.human_developing_soft.productcalc.review.domain.sBeginUseApp
import java.util.Date
import kotlin.reflect.KClass

const val sReviewGlobal = "review"
const val sFirstRun = "firstRun"

class MainApp : Application(), AnalyticsApp {
    private val mMainNavigation = Navigation.Navigation
    private var mAnalytic: FirebaseAnalytics? = null
    lateinit var appComponent: ApplicationComponent
    val components = mutableMapOf<KClass<*>, Any>()

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent
            .factory()
            .create(applicationContext, Firebase.remoteConfig)
        val shell = SharedPreferencesShell.Base(applicationContext, sReviewGlobal)
        if (shell.valueBoolean(sFirstRun, true)) {
            shell.edit {
                it.putLong(sBeginUseApp, Date().time)
                it.putBoolean(sFirstRun, false)
                it.apply()
            }
        }
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 60 else 14_400 // 4 hours
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        if (!BuildConfig.DEBUG) {
            mAnalytic = FirebaseAnalytics.getInstance(this)
        }
    }

    override fun instance(): FirebaseAnalytics? {
        return mAnalytic
    }

    companion object {
        const val BOT_KEY = "BOT_KEY"
        const val GROUP_ID = "GROUP_ID"
    }
}