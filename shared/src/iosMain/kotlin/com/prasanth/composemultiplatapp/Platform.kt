package com.prasanth.composemultiplatapp

import androidx.compose.ui.window.Application
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

fun composeApplication() = Application {
    MainScreen()
}
