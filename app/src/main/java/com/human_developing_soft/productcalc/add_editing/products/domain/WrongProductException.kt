package com.human_developing_soft.productcalc.add_editing.products.domain

import java.lang.Exception

class WrongProductException(
    mMessage: String
) : Exception(mMessage)