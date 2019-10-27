package com.akulinski.crimetivity.citylocationservice.core.domain

import java.io.Serializable

data class Geometry(
        val bounds: Bounds,
        val location: Location,
        val location_type: String,
        val viewport: Viewport
): Serializable