package com.akulinski.crimetivity.citylocationservice.core.domain

import java.io.Serializable


data class GoogleApiResponse(
        val plus_code: PlusCode,
        val results: List<Result>,
        val status: String
): Serializable