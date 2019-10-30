package com.akulinski.crimetivity.citylocationservice.config

import com.akulinski.crimetivity.citylocationservice.core.GeoClient
import com.akulinski.crimetivity.citylocationservice.core.domain.GoogleApiResponse
import org.springframework.stereotype.Component

@Component
class GeoClientFallback : GeoClient {
    override fun getGeoData(map: Map<String, String>): GoogleApiResponse {
        return GoogleApiResponse()
    }
}