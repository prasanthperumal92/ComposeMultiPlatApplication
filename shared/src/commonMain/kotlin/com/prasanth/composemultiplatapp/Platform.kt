package com.prasanth.composemultiplatapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform