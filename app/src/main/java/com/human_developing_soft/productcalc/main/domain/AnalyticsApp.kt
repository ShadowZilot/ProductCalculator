package com.human_developing_soft.productcalc.main.domain

import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
interface AnalyticsApp {
    fun instance(): FirebaseAnalytics?
}