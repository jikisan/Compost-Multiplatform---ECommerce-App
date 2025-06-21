package org.jikisan.cmpecommerceapp

import androidx.compose.ui.window.ComposeUIViewController
import org.jikisan.cmpecommerceapp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }