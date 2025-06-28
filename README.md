
# Prueba Pirani - Juan Cuero

## Alert Microservice - `alert-ms`

Este microservicio permite crear, consultar y eliminar alertas, utilizando Kotlin, Spring Boot y MongoDB.


---

## 📝 Descripción de la solución

Desarrollé un microservicio modular para la gestión de alertas utilizando **Kotlin + Spring Boot**, siguiendo principios de arquitectura limpia (separando `domain`, `application`, `infrastructure`, etc.).

Aunque en el enunciado se solicitaban endpoints tipo `POST` con acciones específicas (por ejemplo: `/alerts/create-alert`), opté por seguir **convenciones REST estándar** (`POST /alerts`, `GET /alerts/{id}`, etc.) ya que es más limpio, mantenible y ampliamente utilizado en la industria.

Implementé el **CRUD completo de Alertas** con campos como: `id`, `usuario`, `condición`, `canal de notificación` y `estado`.

- Configuré la persistencia con **MongoDB**, incluyendo el mapeo entidad-documento.
- Apliqué **pruebas unitarias con 100% de cobertura** en cada capa (`service`, `controller`, `repository`).
- Configuré **Jacoco** para generar reportes.
- **Dockericé** el proyecto con `Dockerfile` y `docker-compose.yml` para levantar MongoDB + el servicio.
- El archivo `README.md` documenta la estructura del proyecto, endpoints, evidencias y logs.

---

## 🔧 Pendientes

- Integración de **autenticación JWT** (omitida por simplicidad y tiempo).
- Endpoint simulado `/action-listener` **no fue implementado**, ya que no interactúa directamente con el CRUD.
- Simulación de despliegue en **AWS Lambda con API Gateway** quedó fuera del alcance, pero mencionaría cómo lo abordaría en producción.
---

## 📁 Estructura del Proyecto

```bash
alert-ms/
├── application/           # Lógica de negocio (services)
│   ├── build.gradle.kts
│   └── src/main/kotlin/com/pirani/alertms/application/service/AlertService.kt
│
├── domain/                # Modelos y repositorios de dominio
│   ├── build.gradle.kts
│   └── src/main/kotlin/com/pirani/alertms/domain/
│       ├── model/Alert.kt
│       ├── repository/AlertRepository.kt
│       └── enums/{ConditionType, NotificationChannel, AlertStatus}.kt
│
├── infrastructure/        # Implementación del repositorio MongoDB
│   ├── build.gradle.kts
│   └── src/main/kotlin/com/pirani/alertms/infrastructure/
│       ├── document/AlertEntity.kt
│       ├── repository/SpringAlertMongoRepository.kt
│       └── repository/impl/AlertMongoRepositoryImpl.kt
│
├── entrypoints/           # Controladores REST
│   ├── build.gradle.kts
│   └── src/main/kotlin/com/pirani/alertms/entrypoints/restapi/AlertController.kt
│
├── bootloader/            # Configuración de Spring Boot
│   ├── build.gradle.kts
│   └── src/main/kotlin/com/pirani/alertms/AlertMsApplication.kt
│
├── build.gradle.kts       # Build script raíz con configuración de Jacoco, JUnit y merge report
```

---

## 📦 Endpoints Disponibles

| Método | Endpoint         | Descripción                   |
|--------|------------------|-------------------------------|
| POST   | `/alerts`        | Crea una nueva alerta         |
| GET    | `/alerts`        | Lista todas las alertas       |
| GET    | `/alerts/{id}`   | Busca una alerta por ID       |
| DELETE | `/alerts/{id}`   | Elimina una alerta por ID     |

---

## 📷 Evidencias de Endpoints

Ejemplo de creación de alerta (`POST /alerts`):

```json
{
  "user": "juan.cuero",
  "condition": "CRITICAL_RISK",
  "notificationChannel": "EMAIL",
  "status": "ACTIVE"
}
```

Respuesta:

```json
{
  "id": "685f2e6dc208fd3acfb4bc89",
  "user": "juan.cuero",
  "condition": "CRITICAL_RISK",
  "notificationChannel": "EMAIL",
  "status": "ACTIVE",
  "createdAt": "2025-06-27T23:51:09.205259361"
}
```

![F1](https://raw.githubusercontent.com/juancuero/alert-ms/refs/heads/main/screens/create.png)

Ejemplo de listar alertas (`GET /alerts`):

Respuesta:

```json
[
    {
        "id": "685f2e6dc208fd3acfb4bc89",
        "user": "juan.cuero",
        "condition": "CRITICAL_RISK",
        "notificationChannel": "EMAIL",
        "status": "ACTIVE",
        "createdAt": "2025-06-27T23:51:09.205"
    }
]
```

![F2](https://raw.githubusercontent.com/juancuero/alert-ms/refs/heads/main/screens/list.png)
---


## 🧪 Ejecutar los Tests

```bash
./gradlew clean test jacocoTestReport
```

- Los reportes individuales estarán en:  
  `module-name/build/reports/jacoco/test/html/index.html`

![F3](https://raw.githubusercontent.com/juancuero/alert-ms/refs/heads/main/screens/tests.png)


## 🐳 Ejecutar con Docker
Para construir la imagen del microservicio y levantarlo junto con MongoDB, ejecuta el siguiente comando desde la raíz del proyecto:

```bash
docker-compose up --build
```
Esto realiza automáticamente el empaquetado del proyecto con Gradle y lanza los servicios definidos en docker-compose.yml:

- MongoDB accesible en mongodb://mongo:27017
- alert-ms expuesto en http://localhost:8080

![F4](https://raw.githubusercontent.com/juancuero/alert-ms/refs/heads/main/screens/docker.png)

![F5](https://raw.githubusercontent.com/juancuero/alert-ms/refs/heads/main/screens/-logs.png)

---

## 📌 Requisitos

- JDK 17
- Gradle 8+
- Docker + Docker Compose

---

