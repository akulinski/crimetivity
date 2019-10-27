package com.akulinski.crimetivity.citylocationservice.core.domain

import java.io.Serializable

data class Viewport(
        val northeast: NortheastX,
        val southwest: SouthwestX
): Serializable