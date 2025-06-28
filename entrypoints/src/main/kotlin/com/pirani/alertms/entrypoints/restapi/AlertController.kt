package com.pirani.alertms.entrypoints.restapi

import com.pirani.alertms.application.service.AlertService
import com.pirani.alertms.domain.model.Alert
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/alerts")
class AlertController(private val alertService: AlertService) {

    private val logger = LoggerFactory.getLogger(AlertController::class.java)

    @PostMapping
    fun create(@RequestBody alert: Alert): ResponseEntity<Alert> {
        logger.info("AlertController Creating alert with data: {}", alert)
        val created = alertService.create(alert)
        logger.info("AlertController Alert created successfully: {}", created)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<Alert>> {
        logger.info("AlertController Retrieving all alerts")
        val result = alertService.findAll()
        logger.info("AlertController Alerts retrieved: {}", result.size)
        return ResponseEntity.ok(result)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<Alert> {
        logger.info("AlertController Retrieving alert by id: {}", id)
        val result = alertService.findById(id)
        return result?.let {
            logger.info("AlertController Alert found: {}", it)
            ResponseEntity.ok(it)
        } ?: run {
            logger.warn("AlertController Alert not found for id: {}", id)
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        logger.info("AlertController Deleting alert with id: {}", id)
        alertService.delete(id)
        logger.info("AlertController Alert with id {} deleted successfully", id)
        return ResponseEntity.noContent().build()
    }
}