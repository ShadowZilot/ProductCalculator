package com.human_developing_soft.productcalc.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.firebase.analytics.FirebaseAnalytics
import com.human_developing_soft.productcalc.MainApp
import com.human_developing_soft.productcalc.main.domain.AnalyticsApp

/**
 * Human Developing Soft
 *
 * @author Egor Ponomarev
 */
abstract class BaseFragment<VIEW_BINDING : ViewBinding, COMPONENT>(
    private val bindingInflater: (inflater: LayoutInflater, container: ViewGroup?) -> VIEW_BINDING
) : Fragment() {
    private var _binding: VIEW_BINDING? = null
    protected val binding: VIEW_BINDING get() = _binding!!
    protected val myApplication get() = requireActivity().application as MainApp

    protected fun analytic(): FirebaseAnalytics? {
        return (requireActivity().application as AnalyticsApp).instance()
    }

    open fun component(): COMPONENT = throw NotImplementedError()

    open fun onComponentReady(component: COMPONENT) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runCatching {
            if (myApplication.components[this@BaseFragment::class] == null) {
                myApplication.components[this@BaseFragment::class] = component() as Any
            }
            myApplication.components[this@BaseFragment::class]?.let {
                @Suppress("UNCHECKED_CAST")
                onComponentReady(it as COMPONENT)
            }
        }
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

    override fun onDestroy() {
        super.onDestroy()
        myApplication.components.remove(this::class)
    }
}