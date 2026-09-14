# BarrioDigital — Microservicio de Catálogo

Microservicio de dominio del sistema BarrioDigital, encargado del catálogo de tipos de trámite y sus cupos
diarios. Construido con Spring Boot y Spring Data JPA sobre Oracle Autonomous Database.

## Responsabilidad

CRUD de tipos de trámite y control del cupo disponible por día. El cupo se descuenta cuando el BFF admite un
trámite de ese tipo, y puede reponerse al máximo configurado.

No valida JWT ni aplica reglas de autorización — eso lo hacen el API Gateway y el BFF. Este servicio es interno y
solo lo consume `ms-barriodigital-bff`.

## Endpoints

- `GET /api/catalog` / `GET /api/catalog/{id}` — lista o consulta tipos de trámite.
- `POST /api/catalog` — crea un tipo de trámite nuevo.
- `PUT /api/catalog/{id}` — actualiza nombre, descripción, requisitos, cupo o si está activo.
- `PATCH /api/catalog/{id}/decrementar-cupo` — descuenta un cupo disponible hoy (409 si ya está en 0).
- `PATCH /api/catalog/{id}/reponer-cupo` — repone el cupo disponible al máximo diario.
- `DELETE /api/catalog/{id}` — elimina un tipo de trámite.

## Configuración

Corre en el puerto `8082`. Necesita las variables de entorno `ORACLE_DB_URL`, `ORACLE_DB_USER` y
`ORACLE_DB_PASSWORD` para conectarse a Oracle Autonomous Database.

## Ejecutar localmente

```bash
./mvnw spring-boot:run
```

## Compilar

```bash
./mvnw clean package
```
