package com.pirani.alertms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication(scanBasePackages = ["com.pirani.alertms"])
@EnableMongoRepositories(basePackages = ["com.pirani.alertms.infrastructure.repository"])
class AlertMsApplication

fun main(args: Array<String>) {
    runApplication<AlertMsApplication>(*args)
}
