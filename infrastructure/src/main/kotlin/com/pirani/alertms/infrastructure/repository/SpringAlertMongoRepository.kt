package com.pirani.alertms.infrastructure.repository

import com.pirani.alertms.infrastructure.document.AlertEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface SpringAlertMongoRepository : MongoRepository<AlertEntity, String>