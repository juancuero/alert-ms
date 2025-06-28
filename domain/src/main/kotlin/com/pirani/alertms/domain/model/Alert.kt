package com.pirani.alertms.domain.model

import com.pirani.alertms.domain.enums.AlertStatus
import com.pirani.alertms.domain.enums.ConditionType
import com.pirani.alertms.domain.enums.NotificationChannel
import java.time.LocalDateTime


data class Alert(
    val id: String? = null,
    val user: String = "",
    val condition: ConditionType = ConditionType.CRITICAL_RISK,
    val notificationChannel: NotificationChannel = NotificationChannel.EMAIL,
    val status: AlertStatus = AlertStatus.ACTIVE,
    val createdAt: LocalDateTime = LocalDateTime.now()
)