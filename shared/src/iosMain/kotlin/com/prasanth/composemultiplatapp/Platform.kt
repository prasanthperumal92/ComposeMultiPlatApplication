package com.prasanth.composemultiplatapp

import androidx.compose.ui.window.Application
import platform.UIKit.UIDevice
import androidx.compose.ui.window.ComposeUIViewController

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

fun composeApplication() = ComposeUIViewController {
    MainScreen()
}
