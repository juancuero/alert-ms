
package com.pirani.alertms

import com.pirani.alertms.domain.enums.AlertStatus
import com.pirani.alertms.domain.enums.ConditionType
import com.pirani.alertms.domain.enums.NotificationChannel
import com.pirani.alertms.domain.model.Alert
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AlertTest {
    @Test
    fun `alert model should store values`() {
        val alert =  Alert("1", "user", ConditionType.CRITICAL_RISK, NotificationChannel.EMAIL, AlertStatus.ACTIVE)
        assertEquals("user", alert.user)
    }
}
