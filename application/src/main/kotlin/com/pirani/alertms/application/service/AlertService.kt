package com.pirani.alertms.application.service

import com.pirani.alertms.domain.model.Alert
import com.pirani.alertms.domain.repository.AlertRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AlertService(private val repository: AlertRepository) {

    private val logger = LoggerFactory.getLogger(AlertService::class.java)

    fun create(alert: Alert): Alert {
        val result = repository.save(alert)
        logger.info("AlertService Alert created: {}", result)
        return result
    }

    fun findAll(): List<Alert> {
        logger.info("AlertService Fetching all alerts")
        val results = repository.findAll()
        logger.info("AlertService Total alerts found: {}", results.size)
        return results
    }

    fun findById(id: String): Alert? {
        logger.info("AlertService Searching alert by ID: {}", id)
        val result = repository.findById(id)
        logger.info("AlertService Result: {}", result)
        return result
    }

    fun delete(id: String) {
        logger.info("AlertService Deleting alert with ID: {}", id)
        repository.delete(id)
        logger.info("AlertService Alert with ID {} deleted", id)
    }
}