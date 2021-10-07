package com.human_developing_sofr.productcalc.ae_products.domain

import java.lang.Exception

class WrongProductException(
    mMessage: String
) : Exception(mMessage)