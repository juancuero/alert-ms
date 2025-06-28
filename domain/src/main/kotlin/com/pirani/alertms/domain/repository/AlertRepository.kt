package com.pirani.alertms.domain.repository

import com.pirani.alertms.domain.model.Alert

interface AlertRepository {
    fun findAll(): List<Alert>
    fun findById(id: String): Alert?
    fun save(alert: Alert): Alert
    fun delete(id: String)
}