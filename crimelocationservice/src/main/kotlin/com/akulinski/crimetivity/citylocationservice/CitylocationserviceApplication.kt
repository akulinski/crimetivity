package com.akulinski.crimetivity.citylocationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableCaching
@EnableDiscoveryClient
class CitylocationserviceApplication

fun main(args: Array<String>) {
	runApplication<CitylocationserviceApplication>(*args)
}
