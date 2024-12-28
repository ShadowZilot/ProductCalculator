package com.human_developing_soft.productcalc.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.human_developing_soft.productcalc.main.domain.AnalyticsApp

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
abstract class BaseFragment<VIEW_BINDING : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?) -> VIEW_BINDING
) : Fragment() {
    private var _binding: VIEW_BINDING? = null
    protected val binding: VIEW_BINDING get() = _binding!!

    protected fun analytic(): FirebaseAnalytics? {
        return (requireActivity().application as AnalyticsApp).instance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}