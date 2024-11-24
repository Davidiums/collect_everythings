# Utilise une image Java de base
FROM openjdk:17-jdk-alpine

# Définit le répertoire de travail
WORKDIR /app

# Copie le fichier WAR généré dans le conteneur
COPY target/gateway-service.war /app/gateway-service.war

# Expose le port sur lequel le service écoutera
EXPOSE 8080

# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-war", "/app/gateway-service.war"]
