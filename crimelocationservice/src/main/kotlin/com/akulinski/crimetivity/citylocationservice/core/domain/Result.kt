package com.akulinski.crimetivity.citylocationservice.core.domain

import java.io.Serializable

data class Result(
        val address_components: List<AddressComponent>,
        val formatted_address: String,
        val geometry: Geometry,
        val place_id: String,
        val types: List<String>
): Serializable