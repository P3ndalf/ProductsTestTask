package com.productstesttask.domain.models

import androidx.compose.ui.graphics.Color

enum class Status(val serverKey: String, val color: Color) {
    AVAILABLE("available", Color.Green),
    OUT_OF_STOCK("out-of-stock", Color.Red),
    COMMING_SOON("comming-soon", Color.Blue);

    companion object {
        infix fun getFromString(stringStatus: String): Status =
            values().find { it.serverKey == stringStatus } ?: throw IllegalStateException("")
    }
}
