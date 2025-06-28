package com.pirani.alertms.infrastructure.document

import com.pirani.alertms.domain.enums.AlertStatus
import com.pirani.alertms.domain.enums.ConditionType
import com.pirani.alertms.domain.enums.NotificationChannel
import com.pirani.alertms.domain.model.Alert
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("alerts")
data class AlertEntity(
    @Id
    val id: String? = null,
    val user: String,
    val condition: String,
    val channel: String,
    val status: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {
    fun toDomain(): Alert = Alert(id, user,
         ConditionType.valueOf(condition),
         NotificationChannel.valueOf(channel),
        AlertStatus.valueOf(status),
        createdAt
    )

    companion object {
        fun from(alert: Alert): AlertEntity =
            AlertEntity(alert.id, alert.user, alert.condition.name, alert.notificationChannel.name, alert.status.name, alert.createdAt)
    }
}