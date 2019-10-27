package com.akulinski.crimetivity.citylocationservice.core.domain

import java.io.Serializable

data class Location(
    val lat: Double,
    val lng: Double
): Serializable