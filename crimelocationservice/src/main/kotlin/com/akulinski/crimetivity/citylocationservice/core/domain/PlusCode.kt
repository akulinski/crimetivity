package com.akulinski.crimetivity.citylocationservice.core.domain

import java.io.Serializable

data class PlusCode(
    val compound_code: String,
    val global_code: String
): Serializable{
    constructor(): this("","")
}