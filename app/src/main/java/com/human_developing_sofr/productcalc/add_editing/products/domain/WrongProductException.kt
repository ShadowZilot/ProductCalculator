package com.human_developing_sofr.productcalc.add_editing.products.domain

import java.lang.Exception

class WrongProductException(
    mMessage: String
) : Exception(mMessage)