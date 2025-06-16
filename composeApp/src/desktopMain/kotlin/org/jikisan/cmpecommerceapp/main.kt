package org.jikisan.cmpecommerceapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.ui.tooling.preview.Preview

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CMP ECommerce App",
    ) {
        App()
    }
}

