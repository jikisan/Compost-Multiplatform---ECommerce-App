package org.jikisan.cmpecommerceapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform