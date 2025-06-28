package com.pirani.alertms.infrastructure.repository.impl

import com.pirani.alertms.domain.model.Alert
import com.pirani.alertms.domain.repository.AlertRepository
import com.pirani.alertms.infrastructure.document.AlertEntity
import com.pirani.alertms.infrastructure.repository.SpringAlertMongoRepository
import org.springframework.stereotype.Repository

@Repository
class AlertMongoRepositoryImpl(
    private val mongoRepo: SpringAlertMongoRepository
) : AlertRepository {
    override fun findAll(): List<Alert> = mongoRepo.findAll().map { it.toDomain() }

    override fun findById(id: String): Alert? = mongoRepo.findById(id).orElse(null)?.toDomain()

    override fun save(alert: Alert): Alert = mongoRepo.save(AlertEntity.from(alert)).toDomain()

    override fun delete(id: String) = mongoRepo.deleteById(id)
}