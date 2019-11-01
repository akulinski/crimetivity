package com.akulinski.crimetivity.zipkinserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import zipkin2.server.internal.EnableZipkinServer

@SpringBootApplication
@EnableZipkinServer
class ZipkinserverApplication

fun main(args: Array<String>) {
	runApplication<ZipkinserverApplication>(*args)
}
