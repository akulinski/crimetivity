package com.akulinski.crimetivity.citylocationservice.core

import com.akulinski.crimetivity.citylocationservice.core.domain.GoogleApiResponse
import feign.QueryMap
import feign.RequestLine
import org.springframework.cache.annotation.Cacheable

interface GeoClient {

    @RequestLine("GET /json")
    @Cacheable("google-responses", key="#map.get('latlng')")
    fun getGeoData(@QueryMap map: Map<String, String>): GoogleApiResponse
}