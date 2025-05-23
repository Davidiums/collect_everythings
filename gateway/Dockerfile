## === Étape 1 : Construction de l'application ===
#FROM maven:3.8.5-openjdk-17 AS build
#WORKDIR /app
## Copie des fichiers Maven et du code source
#COPY pom.xml .
#COPY src ./src
## Compilation et packaging sans tests (pour gagner du temps)
#RUN mvn clean package -DskipTests
#
## === Étape 2 : Image d'exécution ===
#FROM openjdk:17-jdk-alpine
#WORKDIR /app
## Copie du WAR généré depuis l'étape de build
#COPY --from=build /app/target/gateway-service.jar /app/gateway-service.jar
#EXPOSE 8080
## Utilisation de -jar pour lancer le WAR exécutable Spring Boot
#ENTRYPOINT ["java", "-jar", "/app/gateway-service.war"]
