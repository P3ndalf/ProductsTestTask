package com.productstesttask.data.models

import androidx.annotation.DrawableRes
import com.example.productstesttask.R

enum class Status(@DrawableRes val resId: Int) {
    AVAILABLE(R.drawable.product_status_available),
    OUT_OF_STOCK(R.drawable.product_status_outofstock),
    COMMING_SOON(R.drawable.product_status_commingsoon)
}
