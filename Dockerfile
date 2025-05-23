# syntax=docker/dockerfile:1.4

###############################
# Stage 1: Build global du projet
###############################
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /build
# Copie du pom parent et des poms des modules
COPY pom.xml .
COPY gateway/pom.xml gateway/
# Copie de l’ensemble du code source
COPY . .
# Construction de tous les modules (WAR générés dans chaque module)
RUN mvn clean package -DskipTests

###############################
# Stage 2: Image du microservice gateway
###############################
FROM openjdk:17-jdk-alpine AS gateway
WORKDIR /app
# On suppose que le WAR du module gateway se trouve ici
COPY --from=build /build/gateway/target/gateway-service.war /app/gateway-service.war
# Exposition du port (optionnel si le routage se fait en interne)
EXPOSE 8080
# Lancement de l’application
ENTRYPOINT ["java", "-jar", "/app/gateway-service.war"]

