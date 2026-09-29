# AYB Auth API – Endpoints

URL base: `http://localhost:8080`
Todas las peticiones y respuestas usan JSON (`Content-Type: application/json`).

## POST /api/auth/registro
Registra un usuario nuevo. La contraseña se guarda cifrada con BCrypt.

Cuerpo:
```json
{ "usuario": "alison_123456", "password": "Clave123" }
```
Reglas: `usuario` obligatorio, máximo 80 caracteres; `password` obligatorio, entre 4 y 100 caracteres.

| Código | Respuesta |
|---|---|
| 201 Created | `{ "success": true, "message": "Registro realizado correctamente" }` |
| 409 Conflict | `{ "success": false, "message": "El usuario ya está registrado" }` |
| 400 Bad Request | Error de validación (formato de error estándar de Spring Boot) |

## POST /api/auth/login
Valida usuario y contraseña.

Cuerpo:
```json
{ "usuario": "alison_123456", "password": "Clave123" }
```

| Código | Respuesta |
|---|---|
| 200 OK | `{ "success": true, "message": "Autenticación satisfactoria" }` |
| 401 Unauthorized | `{ "success": false, "message": "Error en la autenticación" }` |
| 400 Bad Request | Campos vacíos o JSON mal formado |

Otros: `GET /api/auth/login` → 405 Method Not Allowed. Ruta no definida → 404 Not Found.
Consola H2: `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:file:./data/aybdb`, usuario `sa`, sin contraseña).
