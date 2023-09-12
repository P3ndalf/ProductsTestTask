package com.productstesttask.domain.base.navigation

sealed class Destination(val route: String) {
    data object Main : Destination("main")
    data object ProductDetail : Destination("detail/{product_title}") {
        fun build(title: String) = "detail/$title"
    }
}
