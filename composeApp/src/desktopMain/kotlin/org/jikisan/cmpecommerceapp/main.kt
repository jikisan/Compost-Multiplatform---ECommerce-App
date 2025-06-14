package org.jikisan.cmpecommerceapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMP ECommerce App",
    ) {
        MainScreen()
    }
}