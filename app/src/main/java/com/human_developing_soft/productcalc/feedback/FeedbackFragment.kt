package com.human_developing_soft.productcalc.feedback

import com.human_developing_soft.productcalc.databinding.FeedbackFragmentBinding
import com.human_developing_soft.productcalc.main.ui.BaseFragment

class FeedbackFragment : BaseFragment<FeedbackFragmentBinding>(
    bindingInflater = { inflater, container ->
        FeedbackFragmentBinding.inflate(inflater, container, false)
    }
)