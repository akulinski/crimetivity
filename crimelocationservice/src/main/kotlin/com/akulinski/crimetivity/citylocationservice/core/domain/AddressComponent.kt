package com.akulinski.crimetivity.citylocationservice.core.domain

import java.io.Serializable

data class AddressComponent(
    val long_name: String,
    val short_name: String,
    val types: List<String>
): Serializable