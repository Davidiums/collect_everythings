#!/bin/bash

# Exécuter le premier module
mvn -f authentificationService/pom.xml spring-boot:run &

# Exécuter le deuxième module
mvn -f autreModule/pom.xml spring-boot:run &

# Attendre que tous les processus se terminent
wait
