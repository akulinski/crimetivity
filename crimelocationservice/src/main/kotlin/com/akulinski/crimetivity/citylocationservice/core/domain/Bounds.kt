package com.akulinski.crimetivity.citylocationservice.core.domain

import java.io.Serializable

data class Bounds(
        val northeast: Northeast,
        val southwest: Southwest
): Serializable