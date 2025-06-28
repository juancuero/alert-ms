package com.pirani.alertms

import com.pirani.alertms.application.service.AlertService
import com.pirani.alertms.domain.enums.AlertStatus
import com.pirani.alertms.domain.enums.ConditionType
import com.pirani.alertms.domain.enums.NotificationChannel
import com.pirani.alertms.domain.model.Alert
import com.pirani.alertms.domain.repository.AlertRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito

class AlertServiceTest {

    private val repository = Mockito.mock(AlertRepository::class.java)
    private val service = AlertService(repository)

    @Test
    fun `should create alert`() {
        val alert = Alert("1", "test", ConditionType.CRITICAL_RISK, NotificationChannel.EMAIL, AlertStatus.ACTIVE)
        Mockito.`when`(repository.save(alert)).thenReturn(alert)

        val result = service.create(alert)

        assertEquals(alert, result)
        Mockito.verify(repository).save(alert)
    }

    @Test
    fun `should return all alerts`() {
        val alerts = listOf(
            Alert("1", "Alert 1", ConditionType.OPERATIONAL_EVENT, NotificationChannel.SLACK, AlertStatus.ACTIVE),
            Alert("2", "Alert 2", ConditionType.OPERATIONAL_EVENT, NotificationChannel.EMAIL, AlertStatus.INACTIVE)
        )
        Mockito.`when`(repository.findAll()).thenReturn(alerts)

        val result = service.findAll()

        assertEquals(2, result.size)
        assertEquals(alerts, result)
        Mockito.verify(repository).findAll()
    }

    @Test
    fun `should return alert by ID`() {
        val alert = Alert("1", "Alert by ID", ConditionType.CRITICAL_RISK, NotificationChannel.SLACK, AlertStatus.ACTIVE)
        Mockito.`when`(repository.findById("1")).thenReturn(alert)

        val result = service.findById("1")

        assertNotNull(result)
        assertEquals(alert, result)
        Mockito.verify(repository).findById("1")
    }

    @Test
    fun `should delete alert by ID`() {
        service.delete("1")

        Mockito.verify(repository).delete("1")
    }
}
