package com.pirani.alertms.entrypoints.restapi

import com.pirani.alertms.application.service.AlertService
import com.pirani.alertms.domain.enums.AlertStatus
import com.pirani.alertms.domain.enums.ConditionType
import com.pirani.alertms.domain.enums.NotificationChannel
import com.pirani.alertms.domain.model.Alert
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

class AlertControllerTest {

    private val service = Mockito.mock(AlertService::class.java)
    private val controller = AlertController(service)

    private val alert = Alert("1", "Test", ConditionType.CRITICAL_RISK, NotificationChannel.EMAIL, AlertStatus.ACTIVE)

    @Test
    fun `test create alert`() {
        Mockito.`when`(service.create(alert)).thenReturn(alert)

        val response = controller.create(alert)

        assertEquals(HttpStatus.CREATED, response.statusCode)
        assertEquals(alert, response.body)
        Mockito.verify(service).create(alert)
    }

    @Test
    fun `test find all alerts`() {
        val alerts = listOf(alert)
        Mockito.`when`(service.findAll()).thenReturn(alerts)

        val response = controller.findAll()

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(alerts, response.body)
        Mockito.verify(service).findAll()
    }

    @Test
    fun `test find alert by id - found`() {
        Mockito.`when`(service.findById("1")).thenReturn(alert)

        val response = controller.findById("1")

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(alert, response.body)
        Mockito.verify(service).findById("1")
    }

    @Test
    fun `test find alert by id - not found`() {
        Mockito.`when`(service.findById("999")).thenReturn(null)

        val response = controller.findById("999")

        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        assertNull(response.body)
        Mockito.verify(service).findById("999")
    }

    @Test
    fun `test delete alert`() {
        val response = controller.delete("1")

        assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
        Mockito.verify(service).delete("1")
    }
}
