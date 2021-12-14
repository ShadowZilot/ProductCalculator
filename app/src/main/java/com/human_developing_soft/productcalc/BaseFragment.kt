package com.human_developing_soft.productcalc

import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
open class BaseFragment : Fragment() {
    protected fun analytic(): FirebaseAnalytics? {
        return (requireActivity().application as AnalyticsApp).instance()
    }
}