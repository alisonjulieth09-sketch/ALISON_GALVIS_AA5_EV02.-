# Cómo ejecutar las pruebas (GA7-220501096-AA5-EV02)

1. Abre una terminal en esta carpeta y ejecuta:  `mvn spring-boot:run`
   Espera el mensaje "Tomcat started on port 8080".  → pantallazo sección 6.1
2. En Postman: Import → selecciona los dos archivos de la carpeta `postman/`.
   Arriba a la derecha elige el entorno **Local - AYB Auth API**.  → pantallazo sección 6.2
3. Ejecuta las peticiones TC01 a TC14 **en orden** (TC01 crea el usuario que usan TC02, TC07, TC08 y TC10).
   En cada una toma el pantallazo con la respuesta y la pestaña Test Results. Anota código y tiempo en la tabla del documento.
4. Clic derecho en la colección → Run collection → Run. Debe mostrar 54 pruebas Passed.  → pantallazo sección 10
5. Abre http://localhost:8080/h2-console, conecta con `jdbc:h2:file:./data/aybdb`, usuario `sa`, y ejecuta
   `SELECT * FROM USUARIOS;` — la columna PASSWORD debe empezar por `$2a$10$`.  → pantallazo sección 9

Requisitos: JDK 17 o superior y Maven 3.9 (ya tienes Maven en Descargas; agrega su carpeta `bin` al PATH si `mvn` no se reconoce).
