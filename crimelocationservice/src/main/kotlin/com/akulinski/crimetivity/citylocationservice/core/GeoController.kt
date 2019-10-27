package com.akulinski.crimetivity.citylocationservice.core

import com.akulinski.crimetivity.citylocationservice.core.domain.GoogleApiResponse
import feign.Param
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/location")
class GeoController(val geoClient: GeoClient, @Value("\${google.maps.apiKey}") val key: String) {

    @GetMapping
    fun findLocation(@Param("lat") lat: String, @Param("lon") lon: String): ResponseEntity<GoogleApiResponse> {
        val mapOf = mapOf(Pair("latlng", "$lat,$lon"), Pair("key", key))
        return ResponseEntity.ok(geoClient.getGeoData(mapOf))
    }
}