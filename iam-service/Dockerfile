# Etapa de construcción
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package

# Etapa de ejecución
FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder ./app/target/iam-service-0.0.1-SNAPSHOT.jar ./app.jar

# Añadir las variables de entorno JWT
ENV AUTHORIZATION_JWT_SECRET=clave_secreta_para_firmar_jwt_tokens
ENV AUTHORIZATION_JWT_EXPIRATION_DAYS=7

EXPOSE 8005

# Iniciar PostgreSQL y la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
