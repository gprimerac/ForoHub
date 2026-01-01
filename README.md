ForoHub
Challenge Back End Java

ForoHub - Challenge Back End Java Este proyecto es una API REST desarrollada para el Challenge ForoHub de Alura Latam y Oracle Next Education. La aplicación simula el funcionamiento de un foro donde los usuarios pueden interactuar mediante la creación, consulta, actualización y eliminación de tópicos (CRUD), con un sistema de seguridad basado en tokens JWT.

//Funcionalidades Autenticación de Usuarios: Login seguro mediante el método POST en /login.

Seguridad con JWT: Generación y validación de tokens JSON Web Token para proteger las rutas de la API.

Gestión de Tópicos (CRUD):

Listar todos los tópicos.

Registrar un nuevo tópico.

Detallar un tópico específico por ID.

Actualizar información de un tópico.

Eliminar tópicos.

Persistencia de Datos: Conexión con base de datos MySQL.

Validaciones: Reglas de negocio para evitar duplicados y campos obligatorios.

//Tecnologías Utilizadas Java 24 (JDK)

Spring Boot 3.5.9

Spring Security (Autenticación y Autorización)

Spring Data JPA (Persistencia)

Hibernate (ORM)

Flyway/MySQL Connector (Migraciones y Conexión DB)

Auth0 JWT (Manejo de Tokens)

Lombok (Productividad)

instalacion

1 clonar https://github.com/SaraithSanchez/ForoHub.git

2 base de datos: Configurar la Base de Datos: Crea una base de datos en MySQL llamada forohub_dby configura tus credenciales en el archivo src/main/resources/application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/forohub_db spring.datasource.username=root spring.datasource.password= api.security.secret=${JWT_SECRET:mi_clave_secreta_123}

Ejecutar la aplicación: puede usar el comando ./mvnw spring-boot:runo ejecutarla directamente desde IntelliJ IDEA.

Para interactuar con la API, siga estos pasos:

Utilizar Postman Iniciar sesión: envíe un POST /logincon el JSON del usuario para recibir su token de portador.

Autorización: En las siguientes solicitudes podrás acceder a Auth , seleccionar Bearer Token e ingresar el código recibido.

Puntos finales:

POST /topicos- Registrar un tópico.

GET /topicos- Listar todos.

GET /topicos/{id}- Ver detalles.

PUT /topicos/{id}- Editar.

DELETE /topicos/{id}- Eliminar.

Autor - Gabriel Primera
