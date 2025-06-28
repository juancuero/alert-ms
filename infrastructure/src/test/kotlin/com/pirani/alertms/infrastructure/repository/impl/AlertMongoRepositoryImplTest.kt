package com.pirani.alertms.infrastructure.repository.impl

import com.pirani.alertms.domain.enums.AlertStatus
import com.pirani.alertms.domain.enums.ConditionType
import com.pirani.alertms.domain.enums.NotificationChannel
import com.pirani.alertms.domain.model.Alert
import com.pirani.alertms.infrastructure.document.AlertEntity
import com.pirani.alertms.infrastructure.repository.SpringAlertMongoRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class AlertMongoRepositoryImplTest {

    private val springRepo = Mockito.mock(SpringAlertMongoRepository::class.java)
    private val repository = AlertMongoRepositoryImpl(springRepo)

    private val alert = Alert("1", "Test", ConditionType.CRITICAL_RISK, NotificationChannel.EMAIL, AlertStatus.ACTIVE)
    private val entity = AlertEntity.from(alert)

    @Test
    fun `should find all alerts`() {
        Mockito.`when`(springRepo.findAll()).thenReturn(listOf(entity))

        val result = repository.findAll()

        assertEquals(1, result.size)
        assertEquals(alert, result[0])
        Mockito.verify(springRepo).findAll()
    }

    @Test
    fun `should find alert by id`() {
        Mockito.`when`(springRepo.findById("1")).thenReturn(Optional.of(entity))

        val result = repository.findById("1")

        assertEquals(alert, result)
        Mockito.verify(springRepo).findById("1")
    }

    @Test
    fun `should return null if alert not found by id`() {
        Mockito.`when`(springRepo.findById("2")).thenReturn(Optional.empty())

        val result = repository.findById("2")

        assertNull(result)
        Mockito.verify(springRepo).findById("2")
    }

    @Test
    fun `should save alert`() {
        Mockito.`when`(springRepo.save(Mockito.any(AlertEntity::class.java))).thenReturn(entity)

        val result = repository.save(alert)

        assertEquals(alert, result)
        Mockito.verify(springRepo).save(Mockito.any(AlertEntity::class.java))
    }

    @Test
    fun `should delete alert`() {
        repository.delete("1")

        Mockito.verify(springRepo).deleteById("1")
    }
}
